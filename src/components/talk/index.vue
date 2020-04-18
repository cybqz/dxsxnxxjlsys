<template>
  <div>
    <!-- 搜索框 -->
    <div class="searchWrap">
      <input class="searchInput" v-model="message" placeholder="🔎搜一搜，看一看" type="text">
      <div class="searchText putHover" @click="toSearch">搜索</div>
    </div>
    <div class="talk">
      <!-- 内容区 -->
      <div style="position:relative;height:30px;">
        <router-link class="add putHover" to="/addTalk" >
        <img class="add putHover" @click="addTeam()" src="@/assets/images/add.png" alt="">
      </router-link>
      </div>
      <div v-for="(item,i) in dataList" :key='i'>
         <!-- 间隔线 -->
        <div  class="line10"></div>
        <div class="containTop pad15">
          <!-- <img :src="$axios.defaults.baseURL+item.userImg"> -->
          <img src="@/assets/images/touxiang1.png" >
          <div class="textCenter">
            <div class="name">{{item.name}}</div>
            <div class="time">发布时间：{{item.createDateTime}}</div>
          </div>
          <div class="delete" @click="deleteTalk(item.id)">
            <img src="@/assets/images/delete.png">
          </div>
        </div>
        <div class="containCenter pad15">
          <div class="detailText">{{getText(item.content)}} </div>
          
          <div class="detailImg" >
            <div v-for="(img,i) in item.imgList" :key="i">
              <img :src="$axios.defaults.baseURL+img" >
            </div>
          </div>
        </div>
        <div class="containBottom">
          <span  class="">
            <img v-if="item.praise" src="@/assets/images/zanY.png" >
            <img v-else src="@/assets/images/zanN.png" @click="doPraise(item)" class="putHover">
            {{item.praiseCount}}
          </span>
        </div>
      </div>
  
    </div>

  </div>
</template>

<script>
export default {
  name: 'talk',
  data () {
    return {
      message:"",
      dataList:[],
    }
  },
  methods:{
    //管理员删除论坛
    deleteTalk(id){
      let $this = this
      this.$axios({
          method:'post',
          url:'/forummessabe/admindelete',
          data:$this.qs.stringify({    //这里是发送给后台的数据
                id:id
          })
      }).then((response) =>{          //这里使用了ES6的语法
          if(response.data.code =='200'){
            this.$Message.success({
              message: response.data.msg,
            });
            this.loadTalkData()
          }
      })
    },
    addTeam(){
      this.$router.push({
        path: '/addTalk',
      })
    },
    toSearch(){
      this.loadTalkData();
    },
    loadTalkData(){
      let $this = this
      this.$axios({
          method:'post',
          url:'forummessabe/page',
          data:$this.qs.stringify({    //这里是发送给后台的数据
            content:this.message
          })
      }).then((res) =>{ 
        this.dataList = [];         //这里使用了ES6的语法
        this.dataList = res.data.data;
        for(let i in this.dataList){
          this.$set(this.dataList[i], 'imgList', this.dataList[i].img.split(','));
        }
      })
    },
    
    //过滤内容文字多余100字加省略号缩略
    getText(text){
      if(text && text.length>100){
        return text.substring(0,100)+'...';
      }else{
        return text;
      }
    },
    //判断是否显示全文按钮
    checkContent(text){
      return (text && text.length>100);
    },
    readAll(){
      this.$router.push({
        path: '/readAll',
      })
    },
    //点赞操作
    doPraise(item){
      if(item && item.id){
        let $this = this
        this.$axios({
            method:'post',
            url:'forumpraise/praise',
            data:$this.qs.stringify({    //这里是发送给后台的数据
              messageId:item.id
            })
        }).then((res) =>{          //这里使用了ES6的语法
        })
      }
    }
  },
  mounted(){
    this.loadTalkData()
  }
}
</script>

<style scoped lang='less'>
.add{
  position: absolute;
  right: 20px;
  width: 24px;
  height: 24px;
}
.delete{
  width: 30px;
  display: flex;
  align-items: center;
  padding: 10px;
  img{
    width: 20px !important;
    height:20px !important;
    border-radius: 0 !important;
    margin-right: 0;
  }
}
.pad15{
  padding: 30px;
}
.searchWrap{
  padding: 15px 60px;
  display: flex;
  justify-content: center;
  align-items: center;
  .searchInput{
    height: 40px;
    flex: 1;
    border:gainsboro 1px solid;
    border-radius:  3px 0 0 3px;
    padding: 15px 5px;
  }
  .searchText{
    width: 60px;
    height: 40px;
    line-height: 40px;
    border:#26a2ff 1px solid;
    border-radius: 0 3px 3px 0;
    font-size: 14px;
    color: white;
    background: #26a2ff;
  }
}
.line10{
  height: 10px;
  background: ghostwhite;
  margin: 10px 0;
}
.containTop{
  display: flex;
  align-items: center;
  height:50px;
  padding-top: 30px;
  img{
    width: 50px;
    height:50px;
    margin-right: 20px;
    border-radius: 50%;
  }
  .textCenter{
    flex: 1;
    text-align: left;
    .name{
      width: 100%;
      height: 30px;
      line-height: 30px;
      font-size: 16px;
    }
    .time{
      width: 100%;
      font-size: 12px;
      height: 20px;
      line-height: 20px;
      color: #909090;

    }
  }
  
}
.containCenter{
  font-size: 14px;
  text-align: left;
  .blue{
    color: #26a2ff;
    font-size: 16px;
  }
  .detailImg{
    padding-top: 15px;
    width: 100%;
    display: flex;
    div{
      flex: 1;
      img{
        width: 60%;
        height: auto;
      }
    }
  }
  
}
.containBottom{
  margin: 0 15px;
  font-size: 12px;
  height: 30px;
  line-height: 30px;
  color: #909090;
  text-align: right;
  border-top: ghostwhite 1px solid;
  span{
    display: inline-block;
    width: 25%;
    text-align: right;
    margin-left: 15px;
    img{
      height: auto;
      width:16px;
      vertical-align: text-bottom;
    }
  }
}

</style>
