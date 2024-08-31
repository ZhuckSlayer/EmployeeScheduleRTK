package com.example.employeeschedulertk.presentation.weekends

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.employeeschedulertk.R
import com.example.employeeschedulertk.databinding.DayViewBinding
import com.example.employeeschedulertk.databinding.FragmentChooseDaysBinding
import com.example.employeeschedulertk.presentation.EmployeeApplication
import com.example.employeeschedulertk.presentation.ViewModelFactory
import com.example.employeeschedulertk.utils.Month
import com.example.employeeschedulertk.utils.displayText
import com.example.employeeschedulertk.utils.setTextColorRes
import com.google.android.material.snackbar.Snackbar
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.CalendarView
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject


class ChooseDaysFragment : Fragment() {

    private var _binding: FragmentChooseDaysBinding? = null
    private val binding: FragmentChooseDaysBinding
        get() = _binding!!
    private val weekCalendarView: CalendarView
        get() = binding.calendarView

    private val selectedDate = mutableSetOf<LocalDate>()

    lateinit var viewModel: ChooseDaysViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as EmployeeApplication).component
    }

    private val today = LocalDate.now()


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseDaysBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory)[ChooseDaysViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dayOfWeek = daysOfWeek()
        binding.legendLayout.root.children
            .map { it as TextView }
            .forEachIndexed { index, textView ->
                textView.text = weekToRus(dayOfWeek[index].displayText())
                textView.setTextColorRes(R.color.example_1_white)
            }

        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(100)
        val endMonth = currentMonth.plusMonths(100)
        setupWeekCalendar(startMonth, endMonth, currentMonth, dayOfWeek)
        binding.buttonConfirm.setOnClickListener {
            viewModel.insertSchedule(selectedDate.map {
                it.dayOfMonth.toString() + " " + dayTORus(it.dayOfWeek.toString())
            })
            val test = selectedDate.map {
                it.dayOfMonth.toString() + " " + it.dayOfWeek
            }
            val user=selectedDate.toList()
            val num = test.map {
                it.replace(Regex("[^0-9]"), "").toInt()
            }

        }

    }

    private fun setupWeekCalendar(
        startMonth: YearMonth,
        endMonth: YearMonth,
        currentMonth: YearMonth,
        daysOfWeek: List<DayOfWeek>,
    ) {
        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay

            val textView = DayViewBinding.bind(view).dayView

            init {
                view.setOnClickListener {
                    if (day.position == DayPosition.MonthDate) {
                        dateClicked(date = day.date)
                    }
                    viewModel.getScheduleOnDay()
                    lifecycleScope.launch {
                        viewModel.test.collect {
                            for (employee in it){
                                Log.d("TEST",employee.schedule)
                            }
                        }
                    }
                }
            }
        }
        weekCalendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.day = data
                bindDate(data.date, container.textView, data.position == DayPosition.MonthDate)
            }

            override fun create(view: View): DayViewContainer = DayViewContainer(view)
        }
        weekCalendarView.monthScrollListener = { updateTitle() }
        weekCalendarView.setup(startMonth, endMonth, daysOfWeek.first())
        weekCalendarView.scrollToMonth(currentMonth)
    }

    private fun bindDate(date: LocalDate, textView: TextView, isSelectable: Boolean) {
        textView.text = date.dayOfMonth.toString()
        if (isSelectable) {
            when {
                selectedDate.contains(date) -> {
                    textView.setTextColorRes(R.color.primarySecond)
                    textView.setBackgroundResource(R.drawable.example_1_selected_bg)
                }

                today == date -> {
                    textView.setTextColorRes(R.color.example_1_white)
                    textView.setBackgroundResource(R.drawable.example_1_today_bg)
                }

                else -> {
                    textView.setTextColorRes(R.color.textColor)
                    textView.background = null
                }
            }
        } else {
            textView.setTextColorRes(R.color.example_1_white_light)
            textView.background = null
        }
    }

    private fun dateClicked(date: LocalDate) {


        if (selectedDate.contains(date)) {
            selectedDate.remove(date)
        } else if (selectedDate.size < 8) {
            selectedDate.add(date)
        }
        if (selectedDate.size > 7) {
            Snackbar.make(
                requireView(),
                "Максимальное число выходных - 8!",
                Snackbar.LENGTH_SHORT
            )
                .setBackgroundTint(requireContext().getColor(R.color.black))
                .setTextColor(requireContext().getColor(R.color.example_1_white)).show()
            binding.buttonConfirm.isEnabled = true
        } else {
            binding.buttonConfirm.isEnabled = false
        }
        weekCalendarView.notifyDateChanged(date)
    }

    private fun updateTitle() {
        val month = weekCalendarView.findLastVisibleMonth()?.yearMonth ?: return
        binding.exOneYearText.text = month.year.toString()
        binding.exOneMonthText.text = monthToRus(month.month.toString())
    }

    private fun monthToRus(month: String): String = when (month) {
        "JANUARY" -> Month.JANUARY.month
        "FEBRUARY" -> Month.FEBRUARY.month
        "MARCH" -> Month.MARCH.month
        "APRIL" -> Month.APRIL.month
        "MAY" -> Month.MAY.month
        "JUNE" -> Month.JUNE.month
        "JULY" -> Month.JULY.month
        "AUGUST" -> Month.AUGUST.month
        "DECEMBER" -> Month.DECEMBER.month
        "OCTOBER" -> Month.OCTOBER.month
        "NOVEMBER" -> Month.NOVEMBER.month
        "SEPTEMBER" -> Month.SEPTEMBER.month
        else -> "Месяца не существует"
    }

    private fun weekToRus(week: String) = when (week) {
        "Sun" -> "Вc"
        "Mon" -> "Пн"
        "Tue" -> "Вт"
        "Wed" -> "Ср"
        "Thu" -> "Чт"
        "Fri" -> "Пт"
        "Sat" -> "Сб"
        else -> "Недели не существует"
    }

    private fun dayTORus(day: String) = when (day) {
        "MONDAY" -> "Пн"
        "TUESDAY" -> "Вт"
        "WEDNESDAY" -> "Ср"
        "THURSDAY" -> "Чт"
        "FRIDAY" -> "Пт"
        "SATURDAY" -> "Сб"
        "SUNDAY" -> "Вс"
        else -> "Дня не существует"

    }
}