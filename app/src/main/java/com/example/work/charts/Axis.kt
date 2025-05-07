package com.example.work.charts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.yml.charts.axis.AxisData
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import kotlin.math.ceil


@Composable
fun LineChart(inputPoints: List<Point>){

    val steps = 5
    val maxOrNull = inputPoints.maxOfOrNull { it.y }
    val tope = if(maxOrNull != null) {
        ceil(maxOrNull / 1000).toInt() * 1000
    } else {
        0
    }


    val xAxisData: AxisData = AxisData.Builder()
        .axisStepSize(20.dp)
        .backgroundColor(Color.Transparent)
        .steps(inputPoints.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData: AxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = 1f / steps
            (i * yScale * tope).toInt().toString()  //.formatToSinglePrecision()
        }.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = inputPoints,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White
    )

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = lineChartData,
    )
}


