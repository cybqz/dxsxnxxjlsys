<template>
  <div id="app">
    <!-- 头部导航 -->
    <div v-if="isLogined" class="topWrap">
      <div class="logo">
        <img src="@/assets/images/logo.png" alt="">
        互学
      </div>
      <div class="bar">
        <div v-for="(item,i) in barList" :key="i" 
          :class="isActive == i?'isActive' : ''" 
          @click="changeBar(i)"
          class="barName putHover">
          {{item}}
        </div>
      </div>
      <div @click="quit()" class="quit putHover">
        <img src="@/assets/images/quit.png" alt="">
        退出登录
      </div>
    </div>
    <!-- 内容区和用户卡片 -->
    <div v-if="isLogined" class="botWrap">
      <!-- 内容区 -->
      <div class="left shadow">
        <router-view/>
      </div>
      <!-- 用户卡片 -->
      <div class="right shadow">
        <div  class="user">
					<div class="touxiang">
							<img src="@/assets/images/touxiang1.png" >
					</div>
          <div class="name putHover">
              {{name}}
              <img src="@/assets/images/class.png" >
          </div>
          <div class="describe ">
            <span class="title">简介:</span>
            {{describe}}
            <img src="@/assets/images/edit.png" >
          </div>
				</div>
        <div class="intresting">
          <div class="title">标签:</div>
          <div class="card">
            <span v-for="(item,i) in cardList" :key="i">{{item}}</span>
          </div>
        </div>
        
        <ul class="chooseTab">
          <li>
            <div><img src="@/assets/images/sign.png" ></div>
            <div class="padTOP"><span>签到</span></div>
          </li>
          <li>
            <div><img src="@/assets/images/points.png" ></div>
            <div class="padTOP"><span>积分</span></div>
          </li>
          <li>
            <div><img src="@/assets/images/set.png" ></div>
            <div class="padTOP"><span>设置</span></div>
          </li>
        </ul>
      </div>
    </div>
    <div v-if="!isLogined">
      <router-view/>
    </div>
    
    
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      isLogined:false,
      barList:['首页推荐','共享社区','论坛社区','组队学习'],
      isActive:'0',
      name:'哈哈哈大胖子',
      describe:'点击添加个人介绍，让大家更加了解你~',
      cardList:[
        '文艺青年','美食','文艺青年','美食','文艺青年','美食',
      ]
    }
  },
  created(){
    this.$router.push({
            path: '/',
          })
    let token = sessionStorage.getItem("token");
    if(token ){
      this.isLogined = true
    }else{
      this.isLogined = false
    }
  },
  methods:{
    //退出登录
    quit(){
      sessionStorage.clear();
      this.$router.push({
        path: '/',
      })
    },
    //切换导航
    changeBar(i){
      this.isActive  = i;
      switch (i){
				case 0:
          this.$router.push({
            path: '/',
          })
					break;
				case 1:
					this.$router.push({
						path: '/share',
					})
					break;
				case 2:
					this.$router.push({
						path: '/talk',
					})
					break;
				case 3:
					this.$router.push({
						path: '/team',
					})
					break;		
				default:
					break;
			}
		
    }


  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
html,body,ul,li,ol,dl,dd,dt,p,h1,h2,h3,h4,h5,h6,img {
    margin:0;
    padding:0;
}
html,body{
  font-size: 12px;
}
.shadow{
  box-shadow: inset 0 0 5px 0 gainsboro;
  border-radius: 4px;
}
ul,ol {
    list-style:none
}
.putHover:hover{
 color: #00a6ff;
  cursor: pointer;
}
.pad15{
  padding: 15px;
}
</style>
<style scoped lang='less'>
.topWrap{
  height: 60px;
  display: flex;
  align-items: center;
  padding: 0 15px;
  border-bottom: 1px solid gainsboro;
  .logo{
    text-align: left;
    width: 140px;
    img{
      width: 50px;
      vertical-align: text-bottom;
      margin-right: 5px;
    }
    font-size: 18px;
    color: red;
    font-style: oblique;
  }
  .bar{
    padding: 0 20px;
    padding-top:10px;
    flex: 1;
    display: flex;
    align-items: center;
    font-size: 16px;
    color:orange;
    .barName{
      flex: 1;
      &.isActive{
        color: #00a6ff;
        cursor: pointer;
      }
    }
  }
  .quit{
    padding-top:10px;
    width: 80px;
    text-align: right;
    color:orange;
    img{
      width: 16px;
      vertical-align: text-bottom;
      margin-right: 5px;
    }
  }
}
.putHover:hover{
 color: #00a6ff;
  cursor: pointer;
}

.botWrap{
  width: 80%;
  margin: 60px auto;
  display: flex;
  .left{
    flex: 1;
  }
  .right{
    height: 500px;
    width: 340px;
    padding: 30px 15px;
    margin-left: 80px;
    .user{
      color:orange;
      .touxiang{
        img{
          width: 70px;
        }
      }
      .name{
        height: 40px;
        line-height: 40px;
        font-size: 16px;
        img{
          width: 16px;
          vertical-align: text-bottom;
          margin-left: 5px;
        }
      }
      .describe{
        font-size: 14px;
        color: black;
        text-align: left;
        padding:20px;
        img{
          width: 16px;
          vertical-align: text-bottom;
          margin-left: 5px;
        }
        .title{
          color: orange;
          margin-right: 10px;
        }
      }
    }
    .intresting{
      padding:0 20px;
      text-align: left;
      .title{
        font-size: 14px;
        height: 30px;
        line-height: 30px;
        color: orange;
      }
      .card{
        display: flex;
        align-items: center;
        flex-direction: row;
        flex-wrap: wrap;
        span{
          height: 20px;
          display: flex;
          align-items: center;
          background: gainsboro;
          font-size: 14px;
          margin: 15px;
          padding: 4px;

        }
      }
    }
    .chooseTab{
      margin-top: 15px;
      padding:15px;
      display: flex;
      li{
        flex:1;
        margin: 0 20px;
        div{
          display: flex;
          justify-content: center;
          align-items: center;
          font-size: 16px;
          img{
            width: 30px;
            height: auto;
          }
        }
      }
    }
  }
}


</style>
