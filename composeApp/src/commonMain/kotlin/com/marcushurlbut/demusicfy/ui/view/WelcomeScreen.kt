package com.marcushurlbut.demusicfy.ui.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcushurlbut.demusicfy.resource.BerkshireFontFamily
import com.marcushurlbut.demusicfy.ui.view.util.LiveText
import kotlin.math.roundToInt

@Composable
fun WelcomeScreen(
    onNavigateToChordFinder: () -> Unit,
    onNavigateToMetronome: () -> Unit,
) {
    var showOptions by remember { mutableStateOf(false) }

    if (!showOptions) {
        Text(
            text = "Remove the complexity out of music!",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .padding(horizontal = 32.dp),
        )

        Button(
            onClick = { showOptions = true },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                text = "Get Started",
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    } else {
        var moved by remember { mutableStateOf(false) }
        val pxToMove = with(LocalDensity.current) {
            300.dp.toPx().roundToInt()
        }
        val offset by animateIntOffsetAsState(
            targetValue = if (moved) {
                IntOffset.Zero
            } else {
                IntOffset(0, (pxToMove) * -1)
            },
            label = "offset",
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        LaunchedEffect(Unit) {
            moved = !moved
        }

        LiveText("Welcome Back, Marcus!", style = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = BerkshireFontFamily(),
            fontSize = 28.sp
        ))

        Button(
            onClick = onNavigateToChordFinder,
            content =  {
                Text(
                    text = "Chord Finder",
                    fontFamily = BerkshireFontFamily(),
                    style = MaterialTheme.typography.displayMedium
                )
            },
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                disabledContentColor = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .padding(vertical = 24.dp)
                .offset { offset }
        )
        Button(
            onClick = onNavigateToMetronome,
            content =  {
                Text(
                    text = "Metronome",
                    fontFamily = BerkshireFontFamily(),
                    style = MaterialTheme.typography.displayMedium
                )
            },
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                disabledContentColor = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .padding(vertical = 16.dp)
                .offset { offset }

        )

        HorizontalDivider(color = MaterialTheme.colorScheme.onBackground, thickness = 2.dp)
        ElevatedCard(
            modifier = Modifier
                .padding(16.dp),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            content = {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "This is a fun fact about music, music theory, or capabilities of the app",

                )
            }
        )

        ElevatedCard(
            modifier = Modifier
                .padding(16.dp),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            content = {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "This is a fun fact about music, music theory, or capabilities of the app"
                )
            }
        )

        ElevatedCard(
            modifier = Modifier
                .padding(16.dp),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            content = {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "This is a fun fact about music, music theory, or capabilities of the app"
                )
            }
        )
    }
}