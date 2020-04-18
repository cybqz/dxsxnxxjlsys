<template>
  <div>
    <div class="talkWrap shadow">
    <ul class="talkbox ">
        <li v-for="(item,i) in talkList" :key="i" class="talkList" :class="item.fromUserId == userID?'right':'left'">
            <div :class="item.fromUserId == userID?'right':'left'" class="flexbox">
                <div class="name">{{item.fromName}}:</div>
                <span class="content">{{item.msg}}</span>
            </div>
            
        </li>
        
    </ul>
    <div class="talkwork">
        <input class="rightText"  type="text"  placeholder="" v-model="msg">
        <div class="btn" @click="send">发送</div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'teamTalk',
  data () {
    return {
      title:this.$route.query.title,
      msg:'',
      userID:"",
      userName:"",
      talkId:this.$route.query.talkId,
      talkList:[]
    }
  },
  components:{
  },
  methods:{
    send(){
      if(!this.msg){
        return
      }
      this.sendMessage(this.msg)
    },
    //向服务端发送消息
    sendMessage(msg){
      this.socket.send(JSON.stringify({
        'fromUserId': this.userID,
        'fromTeamId': this.talkId,
        msg: msg
      }));
      let message = {
        fromUserId: this.userID,
        fromTeamId: this.talkId,
        msg: msg,
        fromName:this.userName
      }
      this.talkList.push(message)
      this.msg = '';
    },
    conWebSocket(){
      let vm = this;
      if(window.WebSocket){
          //服务端websocket地址
        vm.socket = new WebSocket('ws://192.168.124.6:9099/teamChatServer/'+this.userID);
        let socket = vm.socket;
        socket.onopen = function(e){
          console.log("连接服务器成功");
        }
        socket.onclose = function(e){
          console.log("服务器关闭");
        }
        socket.onerror = function(){
          console.log("连接出错");
        }
        // 接收服务器的消息
        socket.onmessage = function(e){
          console.log(e)
            if(e.data && e.data !='连接成功'){
                let message = JSON.parse(e.data);
                vm.talkList.push(message);
                console.log(vm.talkList)
            }
          
        }   
      }
    },
    //从local获取用户信息
    loadBaseData(){
      this.userID = sessionStorage.getItem("userId");
      this.userName = sessionStorage.getItem("userName");
    },
    loadteam(){
      let $this = this;
      this.$axios({
          method:'post',
          url:'/team/page',
          data:{}
      }).then((res) =>{          //这里使用了ES6的语法
          this.teamList = res.data.data
      })
    },
   
  },
  mounted(){
    this.loadteam()
    this.loadBaseData()
    this.conWebSocket()
  }
}
</script>

<style scoped lang='less'>
.talkWrap{
    position: relative;
    width: 70%;
    margin: 30px auto;
    padding: 10px 30px;
}
.talkbox{
    padding-top: 10px;
    height: 400px;
    border-bottom:#909090 1px solid;
    margin-bottom: 10px;
}
.talkList{
    font-size: 16px;
    display: flex;
    line-height: 30px;
    margin: 10px 0;
    color: #909090;
    .flexbox{
        width: 70%;
        
        &.right{
            text-align: right;
        }
        &.left{
            text-align: left;
        }
    }
    &.left{
        justify-content: flex-start;
    }
    &.right{
        justify-content: flex-end;
    }
    .name{
        width: 100%;
        color: orange;
        font-size: 16px;
        margin-bottom: 1px;
    }
    .content{
        display: inline-block;
        border-radius: 4px;
        text-align: left;
        padding: 10px;
        border:orange solid 1px;
    }
}
.talkwork{
    
    display: flex;
    font-size: 16px;
    background: white;
    .btn{
        width: 60px;
        height: 40px;
        line-height: 40px;
        border-radius:8px;
        text-align: center;
        color: orange;
        border: orange solid 1px;
        margin-left: 30px;
    }
    .rightText{
        flex: 1;
        border:gainsboro 1px solid;
        border-radius: 4px;
        height: 40px;
        border-radius: 4px;
        padding-left: 30px;
        background: white;
        opacity: .5;
        font-size: 16px;
  }

}
</style>
