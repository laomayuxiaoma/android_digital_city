package com.mhd.home_module.contract.view.fragment


import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.mhd.basekit.viewkit.mvp.view.BaseMvpFragment
import com.mhd.basekit.viewkit.util.route.RouteConstant
import com.mhd.home_module.R
import com.mhd.home_module.contract.icontract.IHomeMainContract
import com.mhd.home_module.contract.model.HomeConfig
import com.mhd.home_module.contract.presenter.HomeMainPresenterImpl
import com.mhd.home_module.databinding.HomeFragmentBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@Route(path = RouteConstant.home_homeFragment)
class HomeFragment : BaseMvpFragment<HomeMainPresenterImpl, HomeConfig, HomeFragmentBinding>(),
        IHomeMainContract.View, View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.home_fragment
    }

    override fun creatPresenter(): HomeMainPresenterImpl {
        return HomeMainPresenterImpl()
    }

    override fun creatConfig(): HomeConfig {
        return HomeConfig()
    }

    override fun initView() {
        base_title.enableLeftShow(false)
        setTitle("首页")
        base_title.setBgAlpha(0f)
//        val imageRightTitle = ImageRightTitle(activity)
//        base_title.titleRightLayout = imageRightTitle
//        imageRightTitle.setImageBackgroundResource(mipmap.app_setting)
//        //设置点击监听(并且未登录时跳转到登录页面)
//        viewProxyClick(this, /*mBinding.llNew,*/ mBinding.llFriend, mBinding.llAuther,mBinding.ivHead,mBinding.tvName)
//        mBinding.llNew.setOnClickListener(this)
//        mBinding.llAuther.setOnClickListener(this)
//        mBinding.ivHead.setOnClickListener(this)
//        mBinding.tvName.setOnClickListener(this)
    }

    override fun init() {
//        EventBus.getDefault().register(this)
//        Common.user?.let {
//            mBinding.tvMePhone.text = it.data.mobile
//            mBinding.ivMeLogin.setImageResource(R.mipmap.loginimg)
//        } ?: noLogin()

    }

    fun noLogin() {
//        mBinding.tvMePhone.text = "登录/注册"
//        mBinding.ivMeLogin.setImageResource(R.mipmap.touxiang)
    }


    override fun replaceLoad() {

    }

    override fun resetView(t: Any?) {

    }

    override fun onClick(p0: View?) {
//        when (p0!!.id) {
//            R.id.llNew -> {//新手指南
//                IntentToActivity.skipActivity(activity, LoginActivity::class.java)
//            }
//            R.id.llFriend -> {//邀请好友
//
//            }
//            R.id.llAuther -> {//认证
//                IntentToActivity.skipActivity(activity, AuthenticationActivity::class.java)
//            }
//            R.id.ivHead , R.id.tvName -> {//认证
//                IntentToActivity.skipActivity(activity, UserinfoActivity::class.java)
//            }
//        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessage(str: String) {
        when (str) {
            "login" -> {
//                mBinding.tvMePhone.text = Common.user.data.mobile
//                mBinding.ivMeLogin.setImageResource(R.mipmap.loginimg)
            }
            "loginout" -> {
                noLogin()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.color_FCAC22).init()
    }
}