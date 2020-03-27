<template>
  <div>
    <!-- 搜索框 -->
    <div class="searchWrap">
      <input class="searchInput" v-model="message" placeholder="🔎搜一搜，看一看" type="text">
      <div class="searchText putHover" @click="toSearch">搜索</div>
    </div>
    <div class="talk">
      <!-- 内容区 -->
      <div v-for="(item,i) in dataList" :key='i'>
         <!-- 间隔线 -->
          <div class="line10"></div>
        <div class="containTop pad15">
          <img :src="$axios.defaults.baseURL+item.userImg">
          <div class="textCenter">
            <div class="name">{{item.name}}</div>
            <div class="time">发布时间：{{item.createDateTime}}</div>
          </div>
        </div>
        <div class="containCenter pad15">
          <div class="detailText">{{getText(item.content)}} <span v-if="item.content.length>100" class="blue"> 全文</span> </div>
          
          <div class="detailImg">
            <!-- <div v-for="(src, index) in item.img" :key="index">
              <img :src="src" >
            </div> -->
            <div class="detailImg" v-if='i%3 ==0'>
          <div>
            <img src="@/assets/images/5.jpg" >
          </div>
          <div>
            <img src="@/assets/images/4.jpg" >
          </div>
          <div>
            <img src="@/assets/images/6.jpg" >
          </div>
          
        </div>
        <div class="detailImg" v-if='i%3 ==2'>
          <div>
            <img src="@/assets/images/7.jpg" >
          </div>
          <div>
            <img src="@/assets/images/8.jpg" >
          </div>
        </div>
        <div class="detailImg" v-if='i%3 ==1'>
          <div>
            <img src="@/assets/images/5.jpg" >
          </div>
        </div>
          </div>
        </div>
        <div class="containBottom">
          <span  class="">
            <img v-if="item.zan =='Y'" src="@/assets/images/zanY.png" >
            <img v-else src="@/assets/images/zanN.png" >
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
      dataList:[
        {
          'name':'你好呀小胖胖',
          'time':'2019-09-09',
          'text':'哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和啊哈哈啊哈啊哈和哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和',
          'img':[require('@/assets/images/5.jpg'),require('@/assets/images/6.jpg'),require('@/assets/images/3.jpg')],
          'zanNum':'2312',
          'zan':'Y',
          'pinglun':'45454'
        }
      ],
     
    }
  },
  methods:{
    toSearch(){
      this.loadTalkData();
    },
    loadTalkData(){
      let $this = this
      this.$axios({
          method:'post',
          url:'forummessabe/page',
          data:$this.qs.stringify({    //这里是发送给后台的数据
            title:this.message
          })
      }).then((res) =>{          //这里使用了ES6的语法
        this.dataList = res.data.data
      })
    },
    
    //过滤内容文字多余100字加省略号缩略
    getText(text){
      if(text.length>100){
        return text.substring(0,100)+'...';
      }else{
        return text;
      }
    },
    readAll(){
      this.$router.push({
        path: '/readAll',
      })
    }
  },
  mounted(){
    this.loadTalkData()
  }
}
</script>

<style scoped lang='less'>
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
