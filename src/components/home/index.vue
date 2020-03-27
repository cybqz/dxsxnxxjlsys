<template>
  <div>
    <div class="tabWrap pad15">
      <div @click="changeTab(1)" :class="isTab == 1?'isTab':''" class="tab1">推荐</div>
      <div class="line">|</div>
      <div @click="changeTab(2)" :class="isTab == 2?'isTab':''" class="tab2">积分榜</div>
    </div>
    <div v-if="isTab == 1" class="pad15">
      <!-- 队伍 -->
      <div class="title green">
        <span class="titleLine">|</span><span>猜你喜欢的队伍</span>
      </div>
      <ul class="party pad30">
          <li v-for='(item,i) in partyList' :key='i'>
            <img class="mrR10" src="@/assets/images/teamIcon.png" >
            <span>{{item.party}}({{item.num}}人)</span>
          </li>
        </ul>


      <!-- 论坛 -->
      <div class="title red">
        <span class="titleLine">|</span><span>猜你喜欢的论坛</span>
      </div>
      <div class="talk">
         <!-- 内容区 -->
        <div v-for="(item,i) in dataList" :key='i'>
          
          <div class="containTop pad15">
            <img src="@/assets/images/touxiang1.png">
            <div class="textCenter">
              <div class="name">{{item.name}}</div>
              <div class="time">发布时间：{{item.time}}</div>
            </div>
          </div>
          <div class="containCenter pad15">
            <div class="detailText">{{getText(item.text)}} <span v-if="item.text.length>100" class="blue"> 全文</span> </div>
            
            <div class="detailImg">
              <div v-for="(src, index) in item.img" :key="index">
                <img :src="src" >
              </div>
            </div>
        

          </div>
          <div class="containBottom">
            <span  class="">
              <img v-if="item.zan =='Y'" src="@/assets/images/zanY.png" >
              <img v-else src="@/assets/images/zanN.png" >
              {{item.zanNum}}
            </span>
            
          </div>

          <!-- 间隔线 -->
          <div class="line10"></div>
          
        </div>
    
      </div>


      <!-- 分享 -->
      <div class="title blue">
        <span class="titleLine">|</span><span>猜你喜欢的分享</span>
      </div>
      <ul class="share pad15">
          <li class="padTOP" v-for='(item,i) in hotList' :key='i' @click="toDetail(selectedShare)">
            <div class="newTop">
                <img :src="item.src">
            </div>
            <div class="newBottom">
              <div class="title">{{item.title}}</div>
              <div class="discrib">{{item.discrib}}</div>
              <div class="time"><span>{{item.time}}</span> 发布</div>
              <div class="priceAndDetail">
                <div class="price">价格：<span v-if="item.price !='面议'&& item.price !='赠送'" class="red">￥</span> <span class="red">{{item.price}}</span></div>
                <div class="detail"><span class="blue">查看详情></span></div>
              </div>
            </div>
          </li>
        </ul>

    </div>
    <div v-if="isTab == 2" class="pad15">
      <div class="title green">
        <span class="titleLine">|</span><span>全网TOP10</span>
      </div>
      <ul class="pointsTOP shadow mrb60">
        <li>
          <div class="topNum">排名</div>
          <div class="topUser">用户名</div>
          <div class="topPoint">分数</div>
          <div class="topClass">等级</div>
        </li>
        <li v-for="(item,i) in topList" :key="i">
          <div class="topNum f48 red">
            <img v-if="i==0" src="@/assets/images/NO1.png" alt="">
            <img v-if="i==1" src="@/assets/images/NO2.png" alt="">
            <img v-if="i==2" src="@/assets/images/NO3.png" alt="">
            <span v-if="i>2">{{i+1}}</span>
          </div>
          <div class="topUser f36 blue">
            {{item.name}}
          </div>
          <div class="topPoint f36 red">
            {{item.points}}
          </div>
          <div class="topClass f32 red">
            <img src="@/assets/images/class.png" >6
          </div>
        </li>
      </ul>
      
      
    </div>

  </div>
</template>

<script>
export default {
  name: 'share',
  data () {
    return {
     isTab:'1',
     partyList:[
        {
          'party':'计算机学习',
          'num':'34'
        },
        {
          'party':'计算机学习',
          'num':'34'
        },
        {
          'party':'计算机学习',
          'num':'34'
        },
        {
          'party':'计算机学习',
          'num':'34'
        }
      ],
      hotList:[
        
        {
          'src':require('@/assets/images/4.jpg'),
          'title':'三国志',
          "discrib":'三国志三国志三国志',
          'price':'赠送',
          "time":'2019-09-09 18:36:42'
        },
        {
          'src':require('@/assets/images/5.jpg'),
          'title':'考研套题全套',
          "discrib":'考研套题全套考研套题全套考研套题全套',
          'price':'面议',
          "time":'2019-09-09 18:36:42'
        },
        {
          'src':require('@/assets/images/1.jpg'),
          'title':'考研笔记',
          "discrib":'去买买的考研笔记去1买的考研笔去买买的2研笔去买买的考研笔3去买买的考研三十个字',
          'price':'123',
          "time":'2019-09-09 18:36:42'

        },
        {
          'src':require('@/assets/images/2.jpg'),
          'title':'三国志',
          "discrib":'三国志三国志三国志',
          'price':'赠送',
          "time":'2019-09-09 18:36:42'
        }

      ],
      dataList:[
        {
          'name':'你好呀小胖胖',
          'time':'2019-09-09',
          'text':'哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和啊哈哈啊哈啊哈和哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和哈哈哈哈哈哈哈哈哈啊哈哈啊哈啊哈和',
          'img':[require('@/assets/images/5.jpg'),require('@/assets/images/6.jpg'),require('@/assets/images/3.jpg')],
          'zanNum':'2312',
          'zan':'Y',
          'pinglun':'45454'
        },
        {
          'name':'你好呀小胖胖',
          'time':'2019-09-09',
          'text':'哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀',
          'img':[require('@/assets/images/8.jpg'),require('@/assets/images/1.jpg')],
          'zanNum':'2312',
          'zan':'N',
          'pinglun':'45454'
        },
        {
          'name':'你好呀小胖胖',
          'time':'2019-09-09',
          'text':'哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀',
          'img':[require('@/assets/images/7.jpg')],
          'zanNum':'2312',
          'zan':'Y',
          'pinglun':'45454'
        },
        {
          'name':'你好呀小胖胖',
          'time':'2019-09-09',
          'text':'哎呀呀呀哎呀呀呀哎呀呀呀哎呀呀呀',
          'img':[require('@/assets/images/2.jpg'),require('@/assets/images/4.jpg')],
          'zanNum':'2312',
          'zan':'N',
          'pinglun':'45454'
        },
      ],
      topList:[],
      
    }
  },
  methods:{
    //切换Tab
    changeTab(num){
      this.isTab = num;
    },
    //过滤内容文字多余100字加省略号缩略
    getText(text){
      if(text.length>100){
        return text.substring(0,100)+'...';
      }else{
        return text;
      }
    },
    loadTop10(){
      let $this = this;
      this.$axios({
          method:'post',
          url:'accumulatepoints/selectSystemTopTen',
          data:{}
      }).then((res) =>{          //这里使用了ES6的语法
          this.topList = res.data.data
      })
    }
  },
  mounted(){
    this.loadTop10()
  }
}
</script>

<style scoped lang='less'>
.tabWrap{
  display: flex;
  align-items: center;
  height: 60px;
  .tab1,.tab2,.line{
    font-size: 14px;
    height: 30px;
    line-height:30px;
    
  }
  .tab1,.tab2{
    border-radius: 5px;
    width: 60px;
    &:hover{
      color: #00a6ff;
      cursor: pointer;
    }
    &.isTab{
      color: #00a6ff;
      cursor: pointer;
    }
  }
  .line{
    margin: 20px;
  }
}
.title{
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
.line10{
  height: 10px;
  background: ghostwhite;
  margin: 10px 0;
}
.party{
  padding: 20px;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
  font-size: 14px;
  li{
    width: 48%;
    height: 30px;
    display: flex;
    text-align: left;
    align-items: center;
    img{
      width: 16px;
      height:16px;
    }
  }
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
.share{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
  padding: 0 30px;
  li{
    width: 40%;
    border-bottom: ghostwhite 0.04rem dashed;
    padding-bottom: 20px;
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
        padding: 10px 0;
        width: 100%;
        font-size: 14px;
        text-align: left;
        color: #909090;
      }
      .price,.time{
        text-align: left;
        height: 30px;
        line-height: 30px;
        font-size: 12px;
        color: #909090;
      }
      .priceAndDetail{
        
        font-size: 12px;
        display: flex;
        color: #909090;
        
        .price{
          height: 0.4rem;
          line-height: 0.4rem;
          flex: 1;
          text-align: left;
        }
        .detail{
          height: 0.4rem;
          line-height: 0.4rem;
          flex: 1;
          text-align: right;
        }
      }
      .red{
          color: red;
          font-size: 0.28rem;
      }
      .blue{
          color: #26a2ff;
          font-size: 0.28rem;
      }
    }
  }

}
.pointsTOP{
  li{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 40px;
    font-size: 14px;
    line-height: 40px;
    &:first-child{
      border-bottom: gainsboro 0.02rem solid;
    }
    .topNum,.topClass,.topPoint{
      display: flex;
      width: 150px;
      height: 40px;
      padding:0 15px;
      justify-content: center;
      align-items: center;
      
    }
    .topNum{
      img{
        width: 20px;
        height: auto;
      }
      
    }
    .topClass{
      img{
        width: 20px;
        height:20px;
      }
    }
    .topUser{
      flex: 1;
      height: 40px;
      line-height: 40px;
      text-align: left;
      padding:0 15px;
    }
  }
}

</style>
