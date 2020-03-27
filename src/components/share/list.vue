<template>
  <div>
    <div class="title  red">
        <span class="titleLine">|</span><span>{{detailTitle}}共享区</span>
      </div>
    <div class="pad30">
      <ul  class="newShare">
        <li class="padTOP shadow" v-for='(item,i) in hotList' :key='i' @click="toDetail(item.title,decodeURI(item.imgSrc),item.id,item.discribe,item.price,item.authorName,item.createDateTime,selectedShare)">
          <div class="newTop">
              <img :src="$axios.defaults.baseURL+item.imgSrc">
          </div>
          <div class="newBottom">
            <div class="title">{{item.title}}</div>
            <div class="discrib">{{item.discribe}}</div>
            <div class="time"><span>{{item.createDateTime}}</span> 发布</div>
            <div class="priceAndDetail">
              <div class="price">价格：<span v-if="item.price !='面议'&& item.price !='赠送'" class="red">￥</span> <span class="red">{{item.price}}</span></div>
              <div class="detail"><span class="blue putHover">查看详情></span></div>
            </div>
          </div>
        </li>
      </ul> 
    </div>
  </div>
</template>

<script>
export default {
  name: 'shareList',
  data () {
    return {
      type:this.$route.query.type||'',
      detailTitle:this.$route.query.type ==1?'书籍':this.$route.query.type ==2?'资料':this.$route.query.type ==3?'笔记':'文具',//接受路由参数，title
      hotList:[],
      
    }
  },
  components:{
  },
  methods:{
    //初始化数据接口
    loadData(){
      // usedbook/page
      let $this = this;
      this.$axios({
          method:'post',
          url:'/shareobject/page',
          data:$this.qs.stringify({    //这里是发送给后台的数据
              flag:$this.type,
          })
      }).then((res) =>{          //这里使用了ES6的语法
          this.hotList = res.data.data
      })
    },
    //查看列表详情
    toDetail:function(title,imgSrc,id,discribe,price,authorName,createDateTime,selectedShare){
      this.$router.push({
        path: '/shareDatail',
        //路由传参
        query: {
          title: title,
          selectedShare:selectedShare,
          authorName:authorName,
          createDateTime:createDateTime,
          discribe:discribe,
          price:price,
          imgSrc:this.$axios.defaults.baseURL+imgSrc
          
        }
      })
    }
  },
  mounted(){
    this.loadData()
  }
  
}
</script>

<style scoped lang='less'>
.addIcon{
  padding-top: 0.1rem;
  img{
    width: 0.5rem;
    height:0.5rem;
  }
}
.pad30{
  padding:  0 30px;
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
.newShare{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
  li{
    width: 40%;
    border-bottom: ghostwhite 1px dashed;
    padding: 20px;
    margin: 20px;
    .newTop{
      width:100%;
      img{
        height: auto;
        width:100%;
      }
    }
    .newBottom{
      .title{
        height: 30px;
        line-height: 30px;
        text-align: center;
        font-size: 16px;
        font-weight:bold;
      }
      .discrib{
        height: 60px;
        padding: 20px 0;
        width: 100%;
        font-size: 14px;
        text-align: left;
        color: #909090;
      }
      .price,.time{
        text-align: left;
        height: 30px;
        line-height: 30px;
        font-size:12px;
        color: #909090;
      }
      .priceAndDetail{
        
        font-size: 14px;
        display: flex;
        color: #909090;
        
        .price{
          height: 30px;
          line-height: 30px;
          flex: 1;
          text-align: left;
        }
        .detail{
          height: 30px;
          line-height: 30px;
          flex: 1;
          text-align: right;
        }
      }
      .red{
          color: red;
          font-size: 14px;
      }
      .blue{
          color: #26a2ff;
          font-size: 14px;
      }
    }
  }

}
</style>
