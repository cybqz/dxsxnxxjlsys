<template>
  <div>
    <!-- 搜索框 -->
    <div class="searchWrap">
      <input class="searchInput" v-model="message" placeholder="🔎搜一搜，看一看" type="text">
      <div class="searchText putHover" @click="toSearch">搜索</div>
    </div>

    <!-- banner -->
    <ul class="chooseTab">
      <li @click="changeList(1)" class="putHover">
        <div><img src="@/assets/images/book.png" ></div>
        <div class="padTOP"><span>书籍</span></div>
      </li>
      <li @click="changeList(2)" class="putHover">
        <div><img src="@/assets/images/material.png" ></div>
        <div class="padTOP"><span>资料</span></div>
      </li>
      <li @click="changeList(3)" class="putHover">
        <div><img src="@/assets/images/note.png" ></div>
        <div class="padTOP"><span>笔记</span></div>
      </li>
      <li @click="changeList(4)" class="putHover">
        <div><img src="@/assets/images/stationery.png" ></div>
        <div class="padTOP"><span>文具</span></div>
      </li>
    </ul>

    <!-- 间隔线 -->
    <div class="line10"></div>

    <div class="tabWrap pad30">
      <div @click="changeTab(1)" :class="selectedShare == 1?'selectedShare':''" class="tab1">最热共享</div>
      <div class="line">|</div>
      <div @click="changeTab(2)" :class="selectedShare == 2?'selectedShare':''" class="tab2">最新共享</div>
    </div>
    <div v-if="selectedShare ==1" class="shareWrap pad30">
      <ul  class="hotShare">
        <li v-for='(item,i) in hotList' :key='i' @click="toDetail(item.title,decodeURI(item.imgSrc),item.id,item.discribe,item.price,item.authorName,item.createDateTime,selectedShare)">
          <div class="hotleft">
              <img :src="$axios.defaults.baseURL+item.imgSrc">
          </div>
          <div class="hotright">
            <div class="title">{{item.title}}</div>
            <div class="discrib">{{item.discribe}}</div>
            <div class="time"><span>{{item.createDateTime}}</span> 发布</div>
            <div class="hotbottom">
              <div class="price">价格：<span v-if="item.price !='面议'&& item.price !='赠送'" class="red">￥</span> <span class="red">{{item.price}}</span></div>
              <div class="detail putHover"><span class="blue ">查看详情></span></div>
            </div>
          </div>
        </li>
      </ul>
    </div>
    
    <ul v-if="selectedShare ==2" class="newShare">
      <li class="padTOP" v-for='(item,i) in newList' :key='i' @click="toDetail(item.title,decodeURI(item.imgSrc),item.id,item.discribe,item.price,item.authorName,item.createDateTime,selectedShare)">
        <div class="newTop">
            <img :src="$axios.defaults.baseURL+item.imgSrc">
        </div>
        <div class="newBottom">
          <div class="title">{{item.title}}</div>
          <div class="discrib">{{item.discribe}}</div>
          <div class="time"><span>{{item.createDateTime}}</span> 发布</div>
          <div class="priceAndDetail">
            <div class="price">价格：<span v-if="item.price !='面议'&& item.price !='赠送'" class="red">￥</span> <span class="red">{{item.price}}</span></div>
            <div class="detail putHover"><span class="blue">查看详情></span></div>
          </div>
        </div>
      </li>
    </ul>
      

  </div>
</template>

<script>
export default {
  name: 'share',
  data () {
    return {
     selectedShare:'1',
     message:'',
      hotList:[],
      newList:[],
    }
  },
  methods:{
     //切换Tab
    changeTab(num){
      this.selectedShare = num;
      this.toSearch()
    },
    //点搜索
    toSearch(){
      if(this.selectedShare == '1'){
        this.loadHotData();
      }
      if(this.selectedShare == '2'){
        this.loadNewData();
      }
    },
    //初始化最新分享数据接口
    loadNewData(){
      let $this = this
      this.$axios({
          method:'post',
          url:'/shareobject/page',
          data:$this.qs.stringify({    //这里是发送给后台的数据
                title:this.message
          })
      }).then((res) =>{          //这里使用了ES6的语法
          this.newList = res.data.data
      })
    },

    //初始化最热分享数据接口
    loadHotData(){
      let $this = this
      this.$axios({
          method:'post',
          url:'/shareobject/hotsearch',
          data:$this.qs.stringify({    //这里是发送给后台的数据
                title:this.message
          })
      }).then((res) =>{          //这里使用了ES6的语法
          this.hotList = res.data.data
      })
    },
    //切换功能区
    changeList(i){
      this.$router.push({
        path: '/shareList',
        //路由传参
        query: {
          type: i,
        }
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
    this.loadHotData();
    this.loadNewData();
  }
}
</script>

<style scoped lang='less'>
.line10{
  height: 10px;
  background: ghostwhite;
}
.pad30{
  padding: 0 15px;
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
.chooseTab{
  padding:15px;
  display: flex;
  li{
    flex:1;
    margin: 0 60px;
    div{
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 16px;
      img{
        width: 30px;
      }
    }
  }
}
.tabWrap{
  display: flex;
  align-items: center;
  height: 60px;
  .tab1,.tab2,.line{
    font-size: 14px;
    height: 30px;
    line-height:30px;
    color: orange;
  }
  .tab1,.tab2{
    border-radius: 5px;
    width: 60px;
    &:hover{
      color: #00a6ff;
      cursor: pointer;
    }
    &.selectedShare{
      color: #00a6ff;
      cursor: pointer;
    }
  }
  .line{
    margin: 20px;
  }
}
.hotShare{
  li{
    display: flex;
    padding: 30px;
    border-bottom: ghostwhite 2px solid;
  }
  .hotleft{
    width: 300px;
    margin-right: 30px;
      img{
        border-radius: 3px;
        width: 210px;
        height: 140px;
      }
  }
  .hotright{
    flex: 1;
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
    .time{
      text-align: left;
      height: 20px;
      line-height: 20px;
      font-size: 12px;
      color: #909090;
    }
    .hotbottom{
      height: 20px;
      line-height: 20px;
      font-size: 12px;
      display: flex;
      color: #909090;
      .price{
        flex: 1;
        text-align: left;
      }
      .detail{
        flex: 1;
        text-align: right;
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
.newShare{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
  padding:0 60px;
  li{
    width: 35%;
    border-bottom: ghostwhite 2px dashed;
    padding: 30px 0;
    .newTop{
      width:100%;
      img{
        height: auto;
        width:100%;
      }
    }
    .newBottom{
      .title{
        height: 40px;
        line-height: 40px;
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
        height: 20px;
        line-height: 20px;
        font-size: 12px;
        color: #909090;
      }
      .priceAndDetail{
        
        font-size: 12px;
        display: flex;
        color: #909090;
        
        .price{
          height: 20px;
          line-height: 20px;
          flex: 1;
          text-align: left;
        }
        .detail{
          height: 20px;
          line-height: 20px;
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
