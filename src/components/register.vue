<template>
  <div class="popLogin">
    <div class="loginWrap">
      <div class="user">
        <input class="rightText" type="text" maxlength='15' placeholder="请输入用户名" v-model="userName">
      </div>
      <div class="password">
        <input class="rightText" type="text" maxlength='15' placeholder="请输入密码" v-model="password">
      </div>
      <div class="sign">
          <div>请选择你喜欢的标签：</div>
          <div class="checkSign">
            <div v-for="(item,i) in checkList" :key="i" class="checkList">
              <div class="checkName">
                {{item.value}}
              </div>
              <div class="checkId">
                <img v-if="item.checkShow" src="@/assets/images/checkY.png"  @click="checkChange(item.id,item.checkShow,i)">
                <img v-if="!item.checkShow"  src="@/assets/images/checkN.png"  @click="checkChange(item.id,item.checkShow,i)">
              </div>
            </div>
          </div>
      </div>
      <div class="btn" @click="login">
        注册
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'register',
  data () {
    return {
      userName:'',
      password:'',
      value:[],
      checkList :[]
      
    }
  },
  components:{
  },
  methods:{
    //选择兴趣
    checkChange(id,flag,index){
      this.value = []
      this.checkList[index].checkShow = !flag
      for( let i in this.checkList){
        if(this.checkList[i].checkShow){
          this.value.push(this.checkList[i].id)
        }
      }
    },
    //load兴趣
    loadData(){
      this.$axios({
          method:'post',
          url:'parames/getInterestAll',
          data:{}
      }).then((response) =>{   //这里使用了ES6的语法
          if(response.data.code =='200'){
            this.checkList = response.data.data
            for( let i in this.checkList){
              this.$set(this.checkList[i], 'checkShow', false);
            }
          }else{
            this.$Message.error(response.data.msg);
          }
      })
    },
    //注册
    login(){
      if(!this.userName){
        this.$Message.error(
          {
            content: '用户名不能为空',
            duration: 2000
          }
        );
        return;
      }
      if(!this.password){
        this.$Message.error(
          {
            content: '密码不能为空',
            duration: 2000
          }
        );
        return
      }
      if(this.value.length == 0){
        this.$Message.error(
          {
            content: '请选择标签！',
            duration: 2000
          }
        );
        return
      }
      let $this = this
      this.$axios({
          method:'post',
          url:'register/register',
          data:$this.qs.stringify({    //这里是发送给后台的数据
                userName:this.userName,
                password:this.password,
                interestList:this.value
          })
      }).then((response) =>{   //这里使用了ES6的语法
          if(response.data.code =='200'){
            this.$Message.success(response.data.msg);
            $this.$router.push({
              path: '/login',
            })
          }else{
            this.$Message.error(response.data.msg);
          }
      })
    },
    
  },
  mounted(){
    this.loadData()
  }
  
}
</script>

<style scoped lang='less'>
.sign{
    font-size: 14px;
    text-align: left;
    color: orange;
}
.checkSign{
    padding: 15px;
    font-size:14px;
    color: #909090;
    .checkList{
      display: flex;
      height: 60px;
      align-items: center;
      .checkName{
        flex: 1;
      }
      .checkId{
        width: 20px;
        img{
          width: 16px;
          height: 16px;
        }
      }
    }
}
.pad30{
  padding:  0 0.3rem;
}
.shadow{
  box-shadow: inset 0 0 0.03rem 0 gainsboro;
  border-radius: 0.1rem;
}
.popLogin{
     width: 100%;
    height: 100%;
    padding-top: 60px;
    display: flex;
    justify-content: center;
    align-items: center;
}
.loginWrap{
 .user,.password{
   width: 280px;
   margin-bottom: 60px;
   display: flex;
   align-items: center;
 }
 .rightText{
    flex: 1;
    width: 200px;
    border:gainsboro 1px solid;
    border-radius: 1px;
    height: 30px;
    border-radius: 5px;
    padding-left: 50px;
    background: white;
    opacity: .5;
    font-size: 14px;
  }
  .btn{
   flex: 1;
   height: 30px;
   color: white;
   font-size: 14px;
   border-radius: 5px;
   line-height: 30px;
   text-align: center;
   background:#00a6ff;
   &.orange{
    background: orange;
  }
   
 }
}
</style>
