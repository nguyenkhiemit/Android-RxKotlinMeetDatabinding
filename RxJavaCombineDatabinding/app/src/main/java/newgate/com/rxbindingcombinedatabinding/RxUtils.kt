package newgate.com.rxbindingcombinedatabinding

import android.databinding.Observable
import android.databinding.Observable.OnPropertyChangedCallback
import android.databinding.ObservableField
import io.reactivex.Flowable
import io.reactivex.BackpressureStrategy
import io.reactivex.FlowableEmitter
import org.intellij.lang.annotations.Flow


/**
 * Created by apple on 8/11/17.
 */
class RxUtils {
    companion object {
        fun <T> toFlowable(observableField: ObservableField<T>): Flowable<T> {
            return Flowable.create({
                emitter: FlowableEmitter<T>? ->
                var callback = object: OnPropertyChangedCallback() {
                    override fun onPropertyChanged(databindingObservable: Observable?, propertyId: Int) {
                        if(databindingObservable == observableField) {
                            emitter?.onNext(observableField.get())
                        }
                    }
                }
                observableField.addOnPropertyChangedCallback(callback)
                emitter?.setCancellable {
                    observableField.removeOnPropertyChangedCallback(callback)
                }
            }, BackpressureStrategy.LATEST)
        }
    }
}