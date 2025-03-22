//import androidx.compose.runtime.Composable
//import com.marcushurlbut.demusicfy.ui.view.metronome.Metronome
//
//// iOS-specific Navigator implementation
//actual class AppNavigator : Navigator {
//    private val backStack = mutableListOf<String>()
//
//    override fun navigateTo(destination: String) {
//        backStack.add(destination)
//    }
//
//    override fun goBack() {
//        if (backStack.isNotEmpty()) {
//            backStack.removeAt(backStack.lastIndex)
//        }
//    }
//
//    override fun currentDestination(): String? = backStack.lastOrNull()
//}
//
//// iOS navigation composable (simplified)
//@Composable
//fun IosNavGraph(navigator: Navigator) {
//    val currentDestination = navigator.currentDestination()
//
//    when (currentDestination) {
//        "guitar" -> GuitarNeck() { navigator.navigateTo("guitar") }
//        "metronome" -> Metronome() { navigator.navigateTo("metronome") }
////        "profile" -> ProfileScreen() { navigator.goBack() }
////        else -> LoginScreen() { navigator.navigateTo("home") }
//    }
//}
////