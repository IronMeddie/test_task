package com.ironmeddie.test_task.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ironmeddie.test_task.R

private val DarkColorPalette = darkColors(

//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = mainOrange,
    secondary = maindarckBlue,
    background = Background,
    onBackground = Background,
)
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
        fontFamily = FontFamily(Font(R.font.mark_pro_new)),
        fontWeight = FontWeight(700),
        fontSize = 16.sp,
        lineHeight = (20.28).sp,
        letterSpacing = (-0.02).sp,
        color = maindarckBlue
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.mark_pro_new)),
        fontWeight = FontWeight(500),
        fontSize = 10.sp,
        lineHeight = (12.68).sp,
        letterSpacing = (-0.03).sp,
        color = PriceWitoutD,
        textDecoration = TextDecoration.LineThrough
    ),

    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.mark_pro_new)),
        fontWeight = FontWeight(500),
        fontSize = 18.sp,
        lineHeight = 23.sp,
        letterSpacing = (-0.02).sp,
        color = maindarckBlue
    ),

    h2 = TextStyle(
        color = maindarckBlue,
        fontSize = 25.sp,
        fontWeight = FontWeight(700),
        fontFamily = FontFamily(Font(R.font.mark_pro_heavy)),
        lineHeight = (31.69).sp,
        letterSpacing = (-0.01).sp
    ),

    h3 = TextStyle(
        color = mainOrange,
        fontSize = 15.sp,
        fontWeight = FontWeight(500),
        fontFamily = FontFamily(Font(R.font.mark_pro_new))
    ),

    h4 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        fontFamily = FontFamily(Font(R.font.mark_pro_new)),
        lineHeight = (15.21).sp,
        letterSpacing = (-0.03).sp
    ),
    h5 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight(500),
        fontFamily = FontFamily(Font(R.font.mark_pro_new))
    ),
    h6 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_pro_d)),
        fontWeight = FontWeight(700),
        fontSize = 25.sp, letterSpacing = (-0.01).sp, lineHeight = 29.83.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.mark_pro_new)),
        fontWeight = FontWeight(400),
        fontSize = 10.sp,
        lineHeight = (12.68).sp,
        letterSpacing = (-0.03).sp,
        color = maindarckBlue
    ),
    subtitle2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.mark_pro_new)),
        fontWeight = FontWeight(400),
        fontSize = 11.sp,
        color = GreyIcons
    )


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