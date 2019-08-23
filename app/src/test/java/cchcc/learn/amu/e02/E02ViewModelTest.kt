package cchcc.learn.amu.e02

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class E02ViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private fun justTrue() = true
    private fun justFalse() = false

    @Test
    fun failed_tryResult_and_applyScore() {
        // given
        // set nextBoolean false
        val viewModel = E02ViewModel(::justFalse)

        // when
        viewModel.tryResult()

        // then
        Assert.assertEquals(E02ViewModel.TryResult.FAILED, viewModel.result.value)

        // when
        viewModel.applyScore()

        //then
        Assert.assertEquals(-1, viewModel.score.value)

        // when
        // called tryResult 2 times
        viewModel.tryResult()
        viewModel.applyScore()

        //then
        Assert.assertEquals(-2, viewModel.score.value)
    }

    @Test
    fun tryResult_and_applyScore() {
        val viewModel = E02ViewModel(::justTrue)

        // given
        // none

        // when
        viewModel.tryResult()

        // then
        Assert.assertEquals(E02ViewModel.TryResult.SUCCESS, viewModel.result.value)

        // given
        // none

        // when
        viewModel.applyScore()

        // then
        Assert.assertEquals(1, viewModel.score.value)
    }

    @Test
    fun clear() {
        val viewModel = E02ViewModel(::justTrue)

        // when
        viewModel.tryResult()
        viewModel.applyScore()

        viewModel.tryResult()
        viewModel.applyScore()

        // then
        Assert.assertEquals(2, viewModel.score.value)

        // when
        viewModel.clear()

        // then
        Assert.assertEquals(Unit, viewModel.clearAction.value)
        Assert.assertEquals(0, viewModel.score.value)
    }

}