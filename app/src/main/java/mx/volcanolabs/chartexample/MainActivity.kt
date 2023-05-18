package mx.volcanolabs.chartexample

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import mx.volcanolabs.chartexample.ui.theme.ChartExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChartExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AndroidView(modifier = Modifier.fillMaxSize(), factory = { context ->
                        val radarChart = PGARadarChart(context)
                        radarChart.apply {
                            webColor = Color.WHITE
                            webLineWidth = 2f
                            webAlpha = 255
                            isRotationEnabled = false
                        }

                        val dataValues = listOf(
                            RadarEntry(4f),
                            RadarEntry(7f),
                            RadarEntry(2f),
                            RadarEntry(5f),
                            RadarEntry(9f),
                            RadarEntry(10f),
                        )
                        val radarDataSet = RadarDataSet(dataValues, "Test 1")
                        radarDataSet.apply {
                            color = Color.parseColor("#3B9EF7")
                            highlightCircleFillColor = Color.parseColor("#3B9EF7")
                            isDrawHighlightCircleEnabled = true
                            isHighlightEnabled = false
                            fillDrawable = context.getDrawable(R.drawable.bg_spark_line)
                            setDrawFilled(true)
                            valueTextColor = Color.parseColor("#3B9EF7")
                        }

                        val radarData = RadarData(
                            listOf(
                                radarDataSet
                            )
                        )

                        // Custom labels for values
                        val labels = listOf(
                            "SG:Total",
                            "SG: Off the Tee",
                            "SG: Approach",
                            "SG: Around the Green",
                            "SG: Putting",
                            "Test"
                        )

                        val xAxis = radarChart.xAxis
                        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
//                        xAxis.textSize = 14f
                        xAxis.axisLineColor = Color.DKGRAY

                        val yAxis = radarChart.yAxis
                        yAxis.isEnabled = false

                        radarChart.data = radarData
                        radarChart
                    })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChartExampleTheme {
        Greeting("Android")
    }
}