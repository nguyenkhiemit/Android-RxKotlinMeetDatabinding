package newgate.com.rxbindingcombinedatabinding

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import newgate.com.rxbindingcombinedatabinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = MainViewModel()
        var mainbinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mainbinding.viewModel = mainViewModel
    }
}
