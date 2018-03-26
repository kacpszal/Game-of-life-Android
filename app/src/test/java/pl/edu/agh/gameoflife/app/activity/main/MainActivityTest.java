package pl.edu.agh.gameoflife.app.activity.main;

import pl.edu.agh.gameoflife.test.RobolectricTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import org.robolectric.Robolectric;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

public class MainActivityTest extends RobolectricTest {

    //@Mock
    //GameOfLifePresenter presenter;

    //MainActivity_ activity = Robolectric.buildActivity(MainActivity_.class).create().get();

    @Before
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
        //activity.presenter = presenter;
    }

    @Test
    public void testDummy() {
        assertNotNull(1);
    }
}
