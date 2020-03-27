<template>
  <div>
    <div class="title red pad30">
      <span class="titleLine">|</span><span>我的队伍</span>
    </div>
    <div class="pad30">
      <ul class="team shadow">
        <li>
          <div class="titleTeam name">队名</div>
          <div class="titleTeam card">二维码</div>
        </li>
        <li v-for="(item,i) in teamList" :key="i">
          <div class="name text">{{item.name}}</div>
          <div class="card text">
            <img @click="showImg(item.id)" src="@/assets/images/erweima.png" alt="">
          </div>
        </li>
      </ul>
    </div>

  </div>
</template>

<script>
export default {
  name: 'team',
  data () {
    return {
     teamList:[
      {
         'name':'考研复习大军',
         'src':require('@/assets/images/erweima.png')
      },
      {
         'name':'考研复习大军',
         'src':require('@/assets/images/erweima.png')
      },
      {
         'name':'考研复习大军',
         'src':require('@/assets/images/erweima.png')
      }
     ]
    }
  },
  methods:{
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
    showImg(id){
      let $this = this;
      this.$axios({
          method:'post',
          url:'/team/getMatrixImage',
          data:$this.qs.stringify({    //这里是发送给后台的数据
              id:$this.id,
          })
      }).then((res) =>{          //这里使用了ES6的语法
          this.ERcardImg = res.data.data
      })
    }
  },
  mounted(){
    this.loadteam()
  }
}
</script>

<style scoped lang='less'>
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
