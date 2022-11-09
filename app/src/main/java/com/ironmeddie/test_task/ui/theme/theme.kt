package com.ironmeddie.test_task.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ironmeddie.test_task.R

private val DarkColorPalette = darkColors(
//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200
)

private val LightColorPalette = lightColors(
    background = Color(R.color.background_main))
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */


@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}

val Shapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(30.dp),
    large = RoundedCornerShape(50.dp)
)



val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.mark_pro_heavy)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.mark_pro_heavy)),
        fontWeight = FontWeight(700),
        fontSize = 10.sp
    ),




    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)


@Composable
fun MyAppTextFieldColors(
    textColor: Color = colorResource(id = R.color.textColorSearch),
    disabledTextColor: Color = colorResource(R.color.white),
    backgroundColor: Color = colorResource(R.color.white),
    cursorColor: Color = colorResource(R.color.white),
    errorCursorColor: Color = colorResource(R.color.white),
    placeholderColor: Color = colorResource(R.color.white),
    disabledPlaceholderColor: Color = colorResource(R.color.white),
    focusedBorderColor: Color = colorResource(R.color.transparent),
    unfocusedBorderColor: Color = colorResource(R.color.transparent)
) = TextFieldDefaults.textFieldColors(
    textColor = textColor,
    disabledTextColor = disabledTextColor,
    backgroundColor = backgroundColor,
    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor,
    placeholderColor = placeholderColor,
    disabledPlaceholderColor = disabledPlaceholderColor,
    focusedIndicatorColor = focusedBorderColor,
    unfocusedIndicatorColor = unfocusedBorderColor

)