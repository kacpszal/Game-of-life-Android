package pl.edu.agh.gameoflife.app.activity.main;

import android.content.Intent;

import pl.edu.agh.gameoflife.test.RobolectricTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class GameOfLifePresenterTest extends RobolectricTest {

    @Mock
    MainActivity view;

    @InjectMocks
    GameOfLifePresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);

    @Test
    public void testDummy() {
        assertThat(true).isEqualTo(true);
    }
}
