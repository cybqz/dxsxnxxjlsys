<template>
  <div style="position:relative">
    <img class="add putHover" @click="addTeam()" src="@/assets/images/add.png" alt="">
    <div class="title red pad30">
      <span class="titleLine">|</span><span>我加入的队伍</span>
    </div>
    <div class="pad30">
      <ul class="team shadow">
        <li>
          <div class="titleTeam name">队名</div>
          <div class="titleTeam card">二维码</div>
        </li>
        <li v-for="(item,i) in teamList" :key="i">
          <div class="name text putHover" @click="toTalk(item.name,item.id)">{{item.name}}</div>
          <div class="card text">
            <img @click="showImg(item.id)" src="@/assets/images/erweima.png" alt="">
          </div>
        </li>
      </ul>
    </div>

    <div class="title red pad30">
      <span class="titleLine">|</span><span>我创建的队伍</span>
    </div>
    <div class="pad30">
      <ul class="team shadow">
        <li>
          <div class="titleTeam name">队名</div>
          <div class="titleTeam card">二维码</div>
        </li>
        <li v-for="(item,i) in myTeamList " :key="i">
          <div class="name text putHover" @click="toTalk(item.name,item.id)">{{item.name}}</div>
          <div class="card text">
            <img @click="showImg(item.id)" src="@/assets/images/erweima.png" alt="">
          </div>
        </li>
      </ul>
    </div>
    <Modal v-model="ERcardImgShow">
      <div class="ERcardImg">
        <img :src="'data:image/png;base64,'+ERcardImg" alt="">
      </div>
      <div class="ERcardImgDetail">
        提示：请在app上进行扫码加入战队！
      </div>
      
    </Modal>
    <Modal v-model="addTeamModel">
      <div class="appModel">
        <div class="setWrap">
          <span class="setTitle">队伍名：</span>
          <input class="rightText" type="text" maxlength='8' placeholder="请添加队伍名，最多8个字" v-model="teamTame">
        </div>
      </div>
      <div class="setBtn appModel" slot="footer">
        <span @click="setCancel" class="popcancel setTitle putHover">取消</span>
        <span @click="setConfirm" class="popconfirm setTitle putHover">确定</span>
      </div>
    </Modal>
  </div>
</template>

<script>
export default {
  name: 'team',
  data () {
    return {
     teamList:[],
     myTeamList:[],
     ERcardImg:'',
     ERcardImgShow:false,
     addTeamModel:false,
     teamTame:'',
    }
  },
  methods:{
    //创建队伍
    addTeam(){
      this.addTeamModel = true;
    },
    //取消创建
    setCancel(){
      this.addTeamModel = false;
      this.teamTame = '';
    },
    //确认创建
    setConfirm(){
      if(!this.teamTame){
        this.$Message.error({
          content: '队伍名不能为空',
          duration: 2000
        });
        return;
      }
      let $this = this
      this.$axios({
          method:'post',
          url:'team/add',
          data:$this.qs.stringify({    //这里是发送给后台的数据
              name:this.teamTame,
          })
      }).then((response) =>{          //这里使用了ES6的语法
          if(response.data.code =='200'){
            this.$Message.success({
              content: response.data.msg,
            });
             this.addTeamModel = false;
            this.$router.go(0);
          }else{
             this.$Message.error({
              content: response.data.msg,
            });
          }
      })
    },
    //点队名聊天
    toTalk(name,talkId){
      this.$router.push({
        path: '/teamTalk',
        //路由传参
        query: {
          title: name,
          talkId:talkId
        }
      })
    },
   loadteam(){
      let $this = this;
      this.$axios({
          method:'post',
          url:'/team/mejoinpage',
          data:{}
      }).then((res) =>{          //这里使用了ES6的语法
          this.teamList = res.data.data
      })
    },
    loadMyteam(){
      let $this = this;
      this.$axios({
          method:'post',
          url:'/team/mecreatepage',
          data:{}
      }).then((res) =>{          //这里使用了ES6的语法
          this.myTeamList = res.data.data
      })
    },
    showImg(id){
      let $this = this;
      this.$axios({
          method:'post',
          url:'/team/getMatrixImage',
          data:$this.qs.stringify({    //这里是发送给后台的数据
              id:id,
          })
      }).then((res) =>{          //这里使用了ES6的语法
          this.ERcardImg = res.data.data
          this.ERcardImgShow = true
      })
    }
  },
  mounted(){
    this.loadteam()
    this.loadMyteam()
  }
}
</script>

<style scoped lang='less'>
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
.add{
  position: absolute;
  right: 20px;
  top: 10px;
  width: 24px;
  height: 24px;
}
.ERcardImgDetail,.ERcardImg{
  padding: 30px;
  text-align: center;
  font-size: 16px;
  color: orange;
}
.pad30{
  padding:0 30px;
}
.title{
  text-align: left;
  font-size: 16px;
  height: 60px;
  line-height: 60px;
  .titleLine{
    margin:0  10px;
  }
  &.red{
    color: orange;
  }
  &.green{
    color: #7eba7e;
  }
  &.blue{
    color: #00a6ff;
  }
  
}
.team{
  width:80%;
  li{
    display: flex;
    align-items: center;
    border-bottom: solid 1px gainsboro;
    .name{
      width: 200px;
      font-size: 14px;
      text-align: center;
    }
    .titleTeam{
      font-size: 14px;
      text-align: center;
      height: 60px;
      line-height: 60px;
      font-weight: bold;
    }
    .text{
      height: 50px;
      line-height: 50px;
    }
    .card{
      flex: 1;
      display: flex;
      align-items: center;
      justify-content: center;
      img{
        width:28px;
        height: 28px;
      }
    }
  }
}


</style>
