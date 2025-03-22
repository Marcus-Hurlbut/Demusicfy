//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.marcushurlbut.demusicfy.ui.view.metronome.Metronome
//
//// Android-specific Navigator implementation
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
//@Composable
//fun AndroidNavGraph(navController: NavHostController, navigator: Navigator) {
//    NavHost(navController = navController, startDestination = "login") {
//        composable("guitar") { GuitarNeck() { navigator.navigateTo("guitar") } }
//        composable("metronome") { Metronome() { navigator.navigateTo("metronome") } }
//    }
//}
