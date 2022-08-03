import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    username:sessionStorage.getItem('username')||'',
    start:sessionStorage.getItem('start')||'',
    end:sessionStorage.getItem('end')||'',


  },
  getters: {
  },
  mutations: {
    setusername(state,payload){
      sessionStorage.setItem('username',payload)
      state.username=payload
    },
    setstart(state,payload){
      sessionStorage.setItem('start',payload)
      state.start=payload},
    setend(state,payload){
      sessionStorage.setItem('end',payload)
      state.end=payload
    },

  },
  actions: {
  },
  modules: {
  }
})
