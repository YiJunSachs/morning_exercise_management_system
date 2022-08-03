import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/global.css'

import BaiduMap from 'vue-baidu-map'
import request from "@/util/request";
Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.prototype.$store=store
Vue.prototype.request=request
Vue.use(BaiduMap, {
  // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
  ak: 'W6gSZkXhIbGAsG7BUZy3thrHBMUsKEfY',

})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
