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
          </div>
          <div class="describe ">
            <span class="title">简介:</span>
            {{discribe?discribe:'还没有自我简介，赶快去设置中设置吧~'}}
          </div>
				</div>
        <div class="intresting">
          <div class="title">标签:</div>
          <div class="card">
            <span v-for="(item,i) in cardList" :key="i">{{item.value}}</span>
          </div>
        </div>
        
        <ul class="chooseTab">
          <li>
            <div><img class="putHover" @click="sign" src="@/assets/images/sign.png" ></div>
            <div class="padTOP putHover" @click="sign"><span>签到</span></div>
          </li>
          <li>
            <div><img class="putHover" @click="lookPoints" src="@/assets/images/points.png" ></div>
            <div class="padTOP putHover" @click="lookPoints"><span >积分</span></div>
          </li>
          <li>
            <div><img class="putHover" @click="set" src="@/assets/images/set.png" ></div>
            <div class="padTOP putHover" @click="set"><span>设置</span></div>
          </li>
        </ul>
      </div>
      <Modal v-model="pointsShow">
        <p>当前积分{{points}}分!当前等级{{Math.ceil(points/100)}}级</p>
      </Modal>
      <Modal v-model="signShow">
        <p style="font-size:16px;" v-if="isSign">
          签到成功!
        </p>
        <p style="font-size:16px;" v-else>
          今天已经签过啦!  明天再来吧~
        </p>
      </Modal>
      <Modal v-model="setModel" width="400" :closable="false" :mask-closable="false">
        <div class="appModel">
          <div class="setWrap">
            <span class="setTitle">用户名：</span>
            <input class="rightText" type="text" maxlength='8' placeholder="请添加用户名，最多8个字" v-model="setname">
          </div>
          <div class="setWrap">
            <span class="setTitle">简  介：</span>
            <input class="rightText" type="text" maxlength='20' placeholder="请添加简介，最多20个字" v-model="setdiscribe">
          </div>
        </div>
        <div class="setBtn appModel" slot="footer">
          <span @click="setCancel" class="popcancel setTitle putHover">取消</span>
          <span @click="setConfirm" class="popconfirm setTitle putHover">确定</span>
        </div>
    </Modal>
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
      barList:['首页推荐','共享社区','论坛社区','组队学习','我的'],
      isActive:'0',
      name:'',
      discribe:'',
      cardList:[],
      points:'1000',
      pointsShow:false,
      setModel:false,
      setname:'',
      setdiscribe:'',
      signShow:false,
      isSign:false,
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
    //获取标签
    loadSign(){
      let $this = this
      this.$axios({
          method:'post',
          url:'interest/getUserInterestAll',
          data:{}
      }).then((res) =>{          //这里使用了ES6的语法
          this.cardList = res.data.data;
      })
    },
    //获取我的数据
    loadData(){
      let $this = this
      this.$axios({
          method:'post',
          url:'user/getUser',
          data:{}
      }).then((res) =>{          //这里使用了ES6的语法
      console.log(res)
          this.name = res.data.userName;
          this.discribe = res.data.introduce;
          this.image = res.data.image
      })

    },
    //积分查看
    lookPoints(){
      this.pointsShow = true;
    },
    //点前到
    sign(){
      let $this = this
      this.$axios({
          method:'post',
          url:'signin/validatesignin',
          data:{}
      }).then((response) =>{   //这里使用了ES6的语法
          if(response.data.code =='200'){
            this.signShow = true
            if(!response.data.data){
              this.isSign = false;
              return
            }else{
              let $this = this
              this.$axios({
                  method:'post',
                  url:'signin/signin',
                  data:{}
              }).then((response) =>{   //这里使用了ES6的语法
                  this.isSign = true;
              })
            }
            
          }
      })
    },
    //点设置
    set(){
      this.setModel = true;
      this.setname = this.name;
      this.setdiscribe = this.discribe;
    },
    //取消设置
    setCancel(){
      this.setModel = false;
      this.setname = '';
      this.setdiscribe = '';
    },
    //确认设置
    setConfirm(){
      if(!this.setname){
        this.$Message.error(
          {
            content: '用户名不能为空',
            duration: 2000
          }
        );
        
        return;
      }
      if(!this.setdiscribe){
        this.$Message.error(
          {
            content: '简介不能为空',
            duration: 2000
          }
        );
      
        return
      }
      let $this = this
      this.$axios({
          method:'post',
          url:'user/update',
          data:$this.qs.stringify({    //这里是发送给后台的数据
                introduce:this.setdiscribe,
                userName:this.setname
          })
      }).then((response) =>{          //这里使用了ES6的语法
          if(response.data.code =='200'){
           this.$Message.success(response.data.msg);
            this.setModel = false;
            this.$router.go(0);
          }
      })
    },
    //退出登录
    quit(){
      this.$axios({
          method:'post', url:'login/logout',
      }).then((res) =>{
        sessionStorage.clear();
        let routerpath = this.$route.fullpath;
        if(routerpath !== '/'){
          console.log('push route ........');
          this.$router.push({path: '/'})
        }
      })
    },
    //切换导航
    changeBar(i){
      this.isActive  = i;
      switch (i){
				case 0:
          this.$router.push({
            path: '/',
            name: 'home'
          })
					break;
				case 1:
					this.$router.push({
            path: '/share',
            name: 'share'
					})
					break;
				case 2:
					this.$router.push({
            path: '/talk',
            name: 'talk'
            
					})
					break;
				case 3:
					this.$router.push({
            path: '/team',
            name: 'team'
					})
          break;	
        case 4:
					this.$router.push({
            path: '/my',
            name: 'my'
					})
					break;  	
				default:
					break;
			}
		
    }
  },
  mounted(){
    this.loadData();
    this.loadSign()
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
.appModel{
  .setWrap,&.setBtn{
    width: 100%;
    height: 30PX;
    margin: 50px auto;
    padding: 15px;
    display: flex;
    align-items: center;
    font-size: 14px;
    justify-content: center;
    .setTitle{
      display: inline-block;
      width: 80px;
      padding: 10px;
      text-align: center;
      border-radius: 4px;
      &.popcancel{
        margin:0 40px;
        border: 1px solid #26a2ff;
        color: orange;
      }
      &.popconfirm{
        margin:0 40px;
        border: 1px solid orange;
        color: white;
        background: orange;
      }
    }
    .rightText{
      flex: 1;
      border:gainsboro 1px solid;
      border-radius: 4px;
      padding: 15px 10px;
    }
  }
}

</style>
