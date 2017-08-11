package newgate.com.rxbindingcombinedatabinding

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

/**
 * Created by apple on 8/11/17.
 */
class MainViewModel {

    var firstName = ObservableField<String>()
    var lastName = ObservableField<String>()
    var helloText = ObservableField<String>()
    var helloButtonState = ObservableBoolean(false)

    constructor() {
         Flowable.combineLatest(RxUtils.toFlowable(firstName), RxUtils.toFlowable(lastName), BiFunction<String, String, Boolean> {
            first, last ->
            StringUtils.isNotNullOrEmpty(first) && StringUtils.isNotNullOrEmpty(last)
        }).subscribe {
             result ->
             helloButtonState.set(result)
             if(!result) {
                 helloText.set(StringUtils.EMPTY)
             }
         }
    }

    fun buttonClicked() {
        helloText.set(String.format("Hello %s %s !", firstName.get(), lastName.get()))
    }

    fun zip2() {
        val obs1 = Flowable.just(1, 3, 5, 7, 9)
        val obs2 = Flowable.just(2, 4, 6)

        val obs = Flowable.combineLatest(obs1, obs2, BiFunction<Int, Int, Int> {
            a, b ->
            3
        })

        obs.subscribe { value -> println("SubscribeValue = " + value) }
    }
}