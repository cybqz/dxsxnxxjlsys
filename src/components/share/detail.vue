<template>
  <div>
    <div class="title  red">
        <span class="titleLine">|</span><span>{{detailTitle}}</span>
      </div>
    <div class="pad30 ">
      <div class="shadow pad30 detail">
        <div class="user ">
          <img src="@/assets/images/touxiang1.png">
          <div class="textCenter">
            <div class="name">{{authorName}}</div>
          </div>
        </div>
        <div class="content">
          <div class="title">共享名称：{{title}}</div>
          <div class="text">
            <div class="price">
              价格：<span v-if="price !='面议'&& price !='赠送'" class="red">￥</span>
              <span class="red">{{price}}</span>
            </div>
             <div class="time"><span>发布时间：{{createDateTime}}</span> </div>
          </div>
          <div class="discribe ">信息描述：{{discribe}}</div>
          <div class="image">
            <img :src="imgSrc">
          </div>
          

        </div>
      </div>  
    </div>
  </div>
</template>

<script>
export default {
  name: 'shareDatail',
  data () {
    return {
      detailTitle:this.$route.query.title+'共享详情',//接受路由参数，title
       title:this.$route.query.title,//接受路由参数，title
      selectedShare:this.$route.query.selectedShare||'',//接受路由参数，上一个页面选择的TabId, selectedShare
      authorName:this.$route.query.authorName||'',//接受路由参数
      createDateTime:this.$route.query.createDateTime||'',//接受路由参数
      discribe:this.$route.query.discribe||'',//接受路由参数
      price:this.$route.query.price||'',//接受路由参数
      imgSrc:this.$route.query.imgSrc||'',//接受路由参数
    }
  },
  components:{
  },
  methods:{
    
  },
  mouted(){
    
  }
  
}
</script>

<style scoped lang='less'>
.pad30{
  padding:  30px ;
}
.title{
  padding: 30px;
  text-align: left;
  font-size: 16px;
  height: 30px;
  line-height: 30px;
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
.detail{
  width: 80%;
  margin: 0 auto;
}
.user{
  display: flex;
  align-items: center;
  height:60px;
  margin-bottom: 15px;
  
  img{
    width: 60px;
    height:60px;
    margin-right: 30px;
    border-radius: 50%;
  }
  .textCenter{
    flex: 1;
    text-align: left;
    align-items: center;
    font-size: 18px;
  }
  
  
}

.content{
  text-align: left;
  border-top: 1px solid gainsboro;
  .title{
    height: 60px;
    line-height: 60px;
    font-size: 16px;
    font-weight:bold;
    margin-bottom: 15px;
  }
  .text{
    display: flex;
    align-items: center;
    height: 60px;
    line-height: 60px;
    font-size: 14px;
    color: #909090;
    .price,.time{
      flex: 1;
    }
    .red{
      color: red;
      font-size: 12px;
    }
  }
  .discribe{
    line-height: 40px;
    font-size: 12px;
  }
  .image{
    img{
      width: 100%;
      height: auto;
    }
  }
}
</style>
