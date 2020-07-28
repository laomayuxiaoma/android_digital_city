package com.mhd.deal_module.contract.view.fragment


import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.mhd.basekit.viewkit.mvp.view.BaseMvpFragment
import com.mhd.basekit.viewkit.util.route.RouteConstant
import com.mhd.deal_module.R
import com.mhd.deal_module.adapter.DealHomeAdapter
import com.mhd.deal_module.contract.icontract.IDealContract
import com.mhd.deal_module.contract.model.DealConfig
import com.mhd.deal_module.contract.model.DealHomeDto
import com.mhd.deal_module.contract.presenter.DealHomePresenterImpl
import com.mhd.deal_module.contract.view.assembly.TextleftTitle
import com.mhd.deal_module.databinding.DealHomeFragmentBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@Route(path = RouteConstant.deal_homeFragment)
class DealFragment : BaseMvpFragment<DealHomePresenterImpl, DealConfig, DealHomeFragmentBinding>(),
        IDealContract.View, View.OnClickListener {

    private var mDealHomeAdapter: DealHomeAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.deal_home_fragment
    }

    override fun creatPresenter(): DealHomePresenterImpl {
        return DealHomePresenterImpl()
    }

    override fun creatConfig(): DealConfig {
        return DealConfig()
    }

    override fun initView() {
        base_title.enableLeftShow(false)
        setTitle("交易")
        base_title.setTitleColor(resources.getColor(R.color.white))
        base_title.setRightTextColor(resources.getColor(R.color.white))
        base_title.titleLeftLayout = TextleftTitle(activity)
        base_title.setRightTextSize(15f)
        base_title.setRightText("我的交易")
        base_title.setRightListener {
            //跳转到我的交易页面
        }
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
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        (mBinding.rvData as RecyclerView).layoutManager = linearLayoutManager
        mDealHomeAdapter = DealHomeAdapter(R.layout.deal_item_data)
        (mBinding.rvData as RecyclerView).adapter = mDealHomeAdapter

        var list = ArrayList<DealHomeDto>()
        for (i in 0 ..9){
            list.add(DealHomeDto())
        }
        mDealHomeAdapter?.addList(list)

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
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.transparent).init()
    }
}