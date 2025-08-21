import com.moviearchive.core.platform.AppContext
import com.moviearchive.navigation.App
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        App(AppContext())
    }
}
