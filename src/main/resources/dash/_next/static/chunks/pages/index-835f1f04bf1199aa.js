(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[405],{8312:function(n,t,e){(window.__NEXT_P=window.__NEXT_P||[]).push(["/",function(){return e(9360)}])},9360:function(n,t,e){"use strict";e.r(t),e.d(t,{default:function(){return _}});var o=e(5893),s=e(9008),a=e.n(s),c=e(4298),i=e.n(c),l=e(1502),r=e.n(l);let d=n=>{let{name:t,id:e,command:s,color:a,index:c}=n;return(0,o.jsxs)("div",{className:r().button,style:{backgroundColor:a},onClick:n=>{if(n.target.className.includes("fa-pen")){console.log("Button ".concat(c," edit clicked"));return}if(n.target.className.includes("fa-trash-can")){console.log("Button ".concat(c," delete clicked"));return}n.target.className.includes("buttonControls")||(fetch("/send",{method:"POST",headers:{"Content-Type":"application/json"},body:e}),console.log("Button ".concat(c," clicked")))},children:[(0,o.jsx)("p",{className:r().buttonName,children:t}),(0,o.jsx)("p",{className:r().buttonCommand,children:s}),(0,o.jsxs)("div",{className:r().buttonControls,children:[(0,o.jsx)("i",{className:"fa-solid fa-pen"}),(0,o.jsx)("i",{className:"fa-regular fa-trash-can"})]})]})},u=()=>(0,o.jsx)("div",{className:r().addButton,onClick:()=>{console.log("Add button clicked")},children:(0,o.jsx)("div",{className:r().addButtonPlus,children:"+"})});var m=e(7294);function _(){let[n,t]=(0,m.useState)([]);function e(){fetch("/get").then(n=>n.json()).then(n=>{t(n.buttons)})}return(0,m.useEffect)(()=>{e();let n=setInterval(e,500);return()=>clearInterval(n)},[]),(0,o.jsxs)(o.Fragment,{children:[(0,o.jsxs)(a(),{children:[(0,o.jsx)("title",{children:"WebDash"}),(0,o.jsx)("meta",{name:"description",content:"A Web Dashboard for your Minecraft server."}),(0,o.jsx)("meta",{name:"viewport",content:"width=device-width, initial-scale=1"}),(0,o.jsx)("link",{rel:"icon",href:"https://simondmc.com/media/webdash-favicon.png"})]}),(0,o.jsx)(i(),{src:"https://kit.fontawesome.com/c7b17cb045.js",crossOrigin:"anonymous"}),(0,o.jsxs)("main",{className:r().main,children:[(0,o.jsx)("h1",{className:r().title,children:"WebDash"}),(0,o.jsx)("div",{className:r().mainBox,children:(0,o.jsxs)("div",{className:r().buttonWrapper,children:[n.map(n=>(0,o.jsx)(d,{name:n.name,id:n.id,command:n.command,color:n.color,index:n.index},n.id)),(0,o.jsx)(u,{})]})})]})]})}},1502:function(n){n.exports={main:"Home_main__nLjiQ",title:"Home_title__T09hD",mainBox:"Home_mainBox__knyAQ",buttonWrapper:"Home_buttonWrapper__PAlwI",button:"Home_button__Zs7A2",buttonControls:"Home_buttonControls__hkjJx",buttonName:"Home_buttonName__QB3_v",buttonCommand:"Home_buttonCommand__UsBHA",addButton:"Home_addButton__BgLWv",addButtonPlus:"Home_addButtonPlus__Y9sVA"}},9008:function(n,t,e){n.exports=e(3121)},4298:function(n,t,e){n.exports=e(3573)}},function(n){n.O(0,[774,888,179],function(){return n(n.s=8312)}),_N_E=n.O()}]);