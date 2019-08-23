package cchcc.learn.amu.mockk

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class MockkTest {
    object MockObj {
        fun add(a: Int, b: Int) = a + b
    }

    @Test
    fun mockkEvery() {
        // aplies mocking to an Object
        mockkObject(MockObj)

        // mockito의 when과 같은 역할
        every { MockObj.add(1, 2) } returns 55

        Assert.assertEquals(55, MockObj.add(1, 2))

        // verify는 every에서 선언한것을 지정한대로 사용했는지, 호출을 했는지 검증(every에서 선언한것을 먼저 사용후에 verify 해야함.)
        verify { MockObj.add(1, 2) }
    }
}