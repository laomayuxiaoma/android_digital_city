package com.mhd.android_digital_city

import android.Manifest
import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.amapservice.MLocationManager
import com.example.muheda.functionkit.perssionkit.OnPermissionListener
import com.example.muheda.functionkit.perssionkit.PerssionUtil
import com.mhd.android_digital_city.adapter.HomeFragmentAdapter
import com.mhd.basekit.viewkit.mvp.contract.model.BaseEventMode
import com.mhd.basekit.viewkit.util.Common
import com.mhd.basekit.viewkit.util.eventbus.EventBusManager
import com.mhd.basekit.viewkit.util.route.RouteConstant
import com.mhd.basekit.viewkit.util.route.RouteUtil
import com.mhd.basekit.viewkit.util.wholeCommon.EventBusVariable
import com.muheda.mhdsystemkit.systemUI.dialog.GeneralDlg
import com.muheda.mhdsystemkit.sytemUtil.uiutil.ToastUtils
import com.umeng.message.PushAgent
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.ArrayList
import kotlin.system.exitProcess

@Route(path = RouteConstant.main_activity)
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val mFragmentList = ArrayList<Fragment>()

    private val TYPE_GENERAL = 0
    private val TYPE_MAP = 0
    private lateinit var tabFragment: Fragment
    private var adapter: HomeFragmentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PushAgent.getInstance(this).onAppStart()
        setContentView(R.layout.activity_main)
        getWindow().setFormat(PixelFormat.TRANSLUCENT)
        EventBusManager.register(this)
        initPermission()
        initFragments()
        initView()

    }

    private fun initPermission() {
        PerssionUtil.perssionRequest(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
            ),
            object :
                OnPermissionListener {
                override fun onPremission() {

                }

                override fun unPremission() {
                    ToastUtils.showShort("请开启相关权限")
                }
            })
    }

    private fun initView() {
        adapter = HomeFragmentAdapter(mFragmentList, supportFragmentManager)
        vp_content.adapter = adapter
        bbl_navigation.setViewPager(vp_content)

        bbl_navigation.setOnItemSelectedListener { bottomBarItem, previousPosition, currentPosition ->
            if (bottomBarItem.getmClickChange() != 0) {
//                showDialog()
//                openZXing()
                Common.user?.let {
                    openZXing()
                }?:noLogin()

            } else {
                val fragment = mFragmentList[currentPosition]
                val fragmentPre = mFragmentList[previousPosition]
                fragment?.onHiddenChanged(false)
                fragmentPre?.onHiddenChanged(true)
            }
        }
    }

    private fun noLogin() {
        RouteUtil.routeSkip(RouteConstant.me_loginActivity, arrayOf())
    }

    private fun openZXing() {
        PerssionUtil.perssionRequest(
            this,
            arrayOf(
                Manifest.permission.CAMERA
            ),
            object :
                OnPermissionListener {
                override fun onPremission() {
//                    RouteUtil.routeSkip(RouteConstant.charge_scan, arrayOf())
                }

                override fun unPremission() {
                    ToastUtils.showShort("请开启相关权限")
                }
            })


    }

    private fun showDialog() {
        GeneralDlg.Builder().hideTitle().hideNegativeButton().setPositiveButton("我知道了")
            .setMessage("扫码充电服务正在筹备中，请您耐心等待").create().showDialog(this)
    }

    private fun initFragments() {
//        mFragmentList.add(RouteUtil.getFragment(RouteConstant.home_fragment))
        mFragmentList.add(RouteUtil.getFragment(RouteConstant.home_homeFragment))
        mFragmentList.add(RouteUtil.getFragment(RouteConstant.deal_homeFragment))
        mFragmentList.add(RouteUtil.getFragment(RouteConstant.me_fragment))
//        tabFragment = RouteUtil.getFragment(RouteConstant.map_fragment)
        tabFragment = Fragment()
//        mFragmentList.add(RouteUtil.getFragment(RouteConstant.DATA_FRAGMENT))
//        mFragmentList.add(RouteUtil.getFragment(RouteConstant.NAVIGATION_FRAGMENT))
//        mFragmentList.add(RouteUtil.getFragment(RouteConstant.USERCENTER_MEFRAGMENT))
    }

    override fun onResume() {
        super.onResume()
        MLocationManager.start(this)
    }

    override fun onClick(v: View?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBusManager.unregister(this)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(datas: BaseEventMode<Any>) {
        when (datas.getType()) {
            EventBusVariable.TAB_CHANGE -> {
//                val fragment = mFragmentList[currentPosition]
//                val fragmentPre = mFragmentList[previousPosition]
//                fragment?.onHiddenChanged(false)
//                fragmentPre?.onHiddenChanged(true)
//                var fragment = mFragmentList[0]
//                mFragmentList[0] = tabFragment
//                tabFragment = fragment
//                adapter!!.updateList(mFragmentList)
//                vp_content.adapter = HomeFragmentAdapter(mFragmentList,supportFragmentManager)
//                bbl_navigation.setViewPager(vp_content)
            }
        }
    }

    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                ToastUtils.showShort("再按一次退出应用")
                mExitTime = System.currentTimeMillis()
            } else {
                exitProcess(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
