package pl.edu.agh.gameoflife.test;

import android.os.Build;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import pl.edu.agh.gameoflife.BuildConfig;

@RunWith(RobolectricGradleTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = Build.VERSION_CODES.JELLY_BEAN,
        packageName = "pl.edu.agh.gameoflife"
)
abstract public class RobolectricTest {

}