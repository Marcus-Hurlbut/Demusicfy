package com.marcushurlbut.demusicfy.ui.view

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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcushurlbut.demusicfy.resource.CursiveFontFamily
import com.marcushurlbut.demusicfy.resource.ToolTipIcon
import com.marcushurlbut.demusicfy.ui.view.util.LiveText
import kotlin.math.roundToInt

@Composable
fun WelcomeScreen(
    onNavigateToChordFinder: () -> Unit,
    onNavigateToMetronome: () -> Unit,
) {
    var showOptions by remember { mutableStateOf(false) }

    if (!showOptions) {
        Button(
            onClick = { showOptions = true },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Get Started",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
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


        var bottomMsgMoved by remember { mutableStateOf(false) }
        val bottomPxToMove = with(LocalDensity.current) {
            300.dp.toPx().roundToInt()
        }
        val bottomMsgOffset by animateIntOffsetAsState(
            targetValue = if (bottomMsgMoved) {
                IntOffset.Zero
            } else {
                IntOffset((bottomPxToMove) * 1, 0)
            },
            label = "offset2",
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        LaunchedEffect(Unit) {
            moved = !moved
            bottomMsgMoved = !bottomMsgMoved
        }

        LiveText("Welcome Back, Marcus!", style = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = CursiveFontFamily(),
            fontSize = 28.sp
        ))

        Button(
            onClick = onNavigateToChordFinder,
            shape = RoundedCornerShape(8.dp),
            content =  {
                Text(
                    text = "Chord Finder",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .fillMaxWidth(.70f)
                .padding(8.dp)
                .padding(vertical = 16.dp)
                .offset { offset }
        )
        Button(
            onClick = onNavigateToMetronome,
            shape = RoundedCornerShape(8.dp),
            content =  {
                Text(
                    text = "Metronome",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .fillMaxWidth(.70f)
                .padding(8.dp)
                .padding(vertical = 16.dp)
                .offset { offset }

        )

        HorizontalDivider(
            color = MaterialTheme.colorScheme.onBackground,
            thickness = 2.dp,
            modifier = Modifier.padding(vertical = 32.dp, horizontal = 28.dp)
        )

        ElevatedCard(
            modifier = Modifier
                .padding(16.dp)
                .offset { bottomMsgOffset },
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
                ToolTipIcon()
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Did you know that 'Dreams' by Fleetwood Mac is in the Lydian mode?",
                    fontSize = 18.sp,
                )
            }
        )

        ElevatedCard(
            modifier = Modifier
                .padding(16.dp)
                .offset { bottomMsgOffset },
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
                ToolTipIcon()
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Unbelievable - There are no ads in Demusicfy!",
                    fontSize = 18.sp,
                )
            }
        )

        ElevatedCard(
            modifier = Modifier
                .padding(16.dp)
                .offset { bottomMsgOffset },
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
                ToolTipIcon()
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "An octave is the interval between one musical pitch and another with half or double its frequency.",
                    fontSize = 18.sp,
                )
            }
        )
    }
}