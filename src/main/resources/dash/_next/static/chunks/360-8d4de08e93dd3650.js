(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[360],{7603:function(e,t,o){"use strict";o.d(t,{Z:function(){return l}});var n=o(5893),a=o(9008),r=o.n(a);function l(){return(0,n.jsxs)(r(),{children:[(0,n.jsx)("title",{children:"WebDash"}),(0,n.jsx)("meta",{name:"description",content:"A Web Dashboard for your Minecraft server"}),(0,n.jsx)("meta",{name:"viewport",content:"width=device-width, initial-scale=1"}),(0,n.jsx)("link",{rel:"icon",href:"https://simondmc.com/media/webdash-favicon.png"})]})}},4564:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.suspense=function(){let e=Error(n.NEXT_DYNAMIC_NO_SSR_CODE);throw e.digest=n.NEXT_DYNAMIC_NO_SSR_CODE,e},t.NoSSR=function(e){let{children:t}=e;return t},(0,o(2648).Z)(o(7294));var n=o(2983)},7645:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=function(e,t){let o=r.default,a={loading:e=>{let{error:t,isLoading:o,pastDelay:n}=e;return null}};e instanceof Promise?a.loader=()=>e:"function"==typeof e?a.loader=e:"object"==typeof e&&(a=n({},a,e)),a=n({},a,t);let u=a.loader,s=()=>null!=u?u().then(l):Promise.resolve(l(()=>null));return a.loadableGenerated&&delete(a=n({},a,a.loadableGenerated)).loadableGenerated,"boolean"!=typeof a.ssr||a.ssr||(delete a.webpack,delete a.modules),o(n({},a,{loader:s}))};var n=o(6495).Z,a=o(2648).Z;a(o(7294));var r=a(o(4588));function l(e){return{default:(null==e?void 0:e.default)||e}}("function"==typeof t.default||"object"==typeof t.default&&null!==t.default)&&void 0===t.default.__esModule&&(Object.defineProperty(t.default,"__esModule",{value:!0}),Object.assign(t.default,t),e.exports=t.default)},3644:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.LoadableContext=void 0;var n=(0,o(2648).Z)(o(7294));let a=n.default.createContext(null);t.LoadableContext=a},4588:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=o(6495).Z,a=(0,o(2648).Z)(o(7294)),r=o(4564),l=o(3644);let u=[],s=[],i=!1;function d(e){let t=e(),o={loading:!0,loaded:null,error:null};return o.promise=t.then(e=>(o.loading=!1,o.loaded=e,e)).catch(e=>{throw o.loading=!1,o.error=e,e}),o}class c{promise(){return this._res.promise}retry(){this._clearTimeouts(),this._res=this._loadFn(this._opts.loader),this._state={pastDelay:!1,timedOut:!1};let{_res:e,_opts:t}=this;e.loading&&("number"==typeof t.delay&&(0===t.delay?this._state.pastDelay=!0:this._delay=setTimeout(()=>{this._update({pastDelay:!0})},t.delay)),"number"==typeof t.timeout&&(this._timeout=setTimeout(()=>{this._update({timedOut:!0})},t.timeout))),this._res.promise.then(()=>{this._update({}),this._clearTimeouts()}).catch(e=>{this._update({}),this._clearTimeouts()}),this._update({})}_update(e){this._state=n({},this._state,{error:this._res.error,loaded:this._res.loaded,loading:this._res.loading},e),this._callbacks.forEach(e=>e())}_clearTimeouts(){clearTimeout(this._delay),clearTimeout(this._timeout)}getCurrentValue(){return this._state}subscribe(e){return this._callbacks.add(e),()=>{this._callbacks.delete(e)}}constructor(e,t){this._loadFn=e,this._opts=t,this._callbacks=new Set,this._delay=null,this._timeout=null,this.retry()}}function p(e){return function(e,t){let o=Object.assign({loader:null,loading:null,delay:200,timeout:null,webpack:null,modules:null,ssr:!0},t);o.lazy=a.default.lazy(o.loader);let n=null;function u(){if(!n){let t=new c(e,o);n={getCurrentValue:t.getCurrentValue.bind(t),subscribe:t.subscribe.bind(t),retry:t.retry.bind(t),promise:t.promise.bind(t)}}return n.promise()}if(!i){let d=o.webpack?o.webpack():o.modules;d&&s.push(e=>{for(let t of d)if(-1!==e.indexOf(t))return u()})}function p(e){!function(){u();let e=a.default.useContext(l.LoadableContext);e&&Array.isArray(o.modules)&&o.modules.forEach(t=>{e(t)})}();let t=o.loading,n=a.default.createElement(t,{isLoading:!0,pastDelay:!0,error:null}),s=o.ssr?a.default.Fragment:r.NoSSR,i=o.lazy;return a.default.createElement(a.default.Suspense,{fallback:n},a.default.createElement(s,null,a.default.createElement(i,Object.assign({},e))))}return p.preload=()=>u(),p.displayName="LoadableComponent",p}(d,e)}function m(e,t){let o=[];for(;e.length;){let n=e.pop();o.push(n(t))}return Promise.all(o).then(()=>{if(e.length)return m(e,t)})}p.preloadAll=()=>new Promise((e,t)=>{m(u).then(e,t)}),p.preloadReady=function(){let e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:[];return new Promise(t=>{let o=()=>(i=!0,t());m(s,e).then(o,o)})},window.__NEXT_PRELOADREADY=p.preloadReady,t.default=p},9360:function(e,t,o){"use strict";o.r(t),o.d(t,{baseUrl:function(){return f},default:function(){return b},key:function(){return h}});var n=o(5893),a=o(4298),r=o.n(a),l=o(1502),u=o.n(l);let s=e=>{let{name:t,id:o,command:a,color:r,fetchData:l,handleDrag:s,handleDrop:i}=e;return(0,n.jsxs)("div",{className:u().button,id:o,draggable:"true",onDragStart:e=>s(e),onDragOver:e=>e.preventDefault(),onDrop:e=>i(e),style:{backgroundColor:r},onClick:async e=>{if(e.target.className.includes("fa-pen")){let n=document.querySelector(".popup.route-popup");n.classList.remove("fade-out"),n.classList.add("fade-in");let u=document.querySelector(".popup.route-popup .name");u.value=t;let s=document.querySelector(".popup.route-popup .command");s.value=a;let i=document.querySelector(".popup.route-popup .color");i.value=r;let d=document.querySelector(".popup.route-popup .id");d.innerHTML=o;let c=document.querySelector(".popup.route-popup .save");c.innerHTML="Save";return}if(e.target.className.includes("fa-trash-can")){await fetch("".concat(f,"/delete"),{method:"DELETE",body:o,headers:{Authorization:h}}),l();return}e.target.className.includes("buttonControls")||fetch("".concat(f,"/send"),{method:"POST",body:o,headers:{Authorization:h}})},children:[(0,n.jsx)("p",{className:u().buttonName,children:t}),(0,n.jsx)("p",{className:u().buttonCommand,children:a}),(0,n.jsxs)("div",{className:u().buttonControls,children:[(0,n.jsx)("i",{className:"fa-solid fa-pen"}),(0,n.jsx)("i",{className:"fa-regular fa-trash-can"})]})]})},i=()=>(0,n.jsx)("div",{className:u().addButton,onClick:()=>{let e=document.querySelector(".popup.route-popup");e.classList.remove("fade-out"),e.classList.add("fade-in");let t=document.querySelector(".popup.route-popup .name");t.value="";let o=document.querySelector(".popup.route-popup .command");o.value="";let n=document.querySelector(".popup.route-popup .color");n.value="#5781af";let a=document.querySelector(".popup.route-popup .save");a.innerHTML="Add"},children:(0,n.jsx)("div",{className:u().addButtonPlus,children:"+"})});var d=o(7294),c=o(5152),p=o.n(c),m=o(7603);let f="",h="",_=p()(()=>o.e(691).then(o.bind(o,691)),{loadableGenerated:{webpack:()=>[691]},ssr:!1});function b(){let[e,t]=(0,d.useState)([]);function o(){fetch("".concat(f,"/get"),{headers:{Authorization:h}}).then(e=>{if(401!==e.status&&403!==e.status)return e.json();window.location.reload()}).then(e=>{let o=document.querySelector(".invalid");o&&"false"!==o.innerHTML?o.innerHTML="false":t(e.buttons)})}(0,d.useEffect)(()=>{var e;let t;return h=null!==(e=new URLSearchParams(window.location.search).get("key"))&&void 0!==e?e:"",o(),fetch("".concat(f,"/period")).then(e=>e.json()).then(e=>{console.info("Fetching buttons every ".concat(e.period,"ms")),t=setInterval(o,e.period)}),()=>clearInterval(t)},[]);let[a,l]=(0,d.useState)(""),c=e=>{l(e.currentTarget.id)},p=o=>{let n=e.find(e=>e.id===a),r=e.find(e=>e.id===o.currentTarget.id),l=n.index,u=r.index;fetch("".concat(f,"/drag"),{method:"POST",body:"".concat(l,"\xa7\xa7\xa7").concat(u),headers:{Authorization:h}}),document.querySelector(".invalid").innerHTML="true";let s=[...e],i=s.find(e=>e.id===a);if(l<u)for(let d=l+1;d<=u;d++)s.find(e=>e.index===d).index--;else for(let c=l-1;c>=u;c--)s.find(e=>e.index===c).index++;i.index=u,t(s)};return(0,n.jsxs)(n.Fragment,{children:[(0,n.jsx)(m.Z,{}),(0,n.jsx)(_,{fetchData:o}),(0,n.jsx)(r(),{src:"https://kit.fontawesome.com/c7b17cb045.js",crossOrigin:"anonymous"}),(0,n.jsxs)("main",{className:u().main,children:[(0,n.jsx)("h1",{className:u().title,children:"WebDash"}),(0,n.jsx)("div",{className:u().mainBox,children:(0,n.jsxs)("div",{className:u().buttonWrapper,children:[[...e].sort((e,t)=>e.index-t.index).map(e=>(0,n.jsx)(s,{name:e.name,id:e.id,command:e.command,color:e.color,fetchData:o,handleDrag:c,handleDrop:p},e.id)),(0,n.jsx)(i,{})]})})]})]})}},1502:function(e){e.exports={main:"Home_main__nLjiQ",title:"Home_title__T09hD",mainBox:"Home_mainBox__knyAQ",buttonWrapper:"Home_buttonWrapper__PAlwI",button:"Home_button__Zs7A2",buttonControls:"Home_buttonControls__hkjJx",buttonName:"Home_buttonName__QB3_v",buttonCommand:"Home_buttonCommand__UsBHA",addButton:"Home_addButton__BgLWv",addButtonPlus:"Home_addButtonPlus__Y9sVA",unauthorized:"Home_unauthorized__uBXup",dashboardOff:"Home_dashboardOff___H21X",copyCode:"Home_copyCode__eY8J3",copiedPopup:"Home_copiedPopup__kTwlZ"}},5152:function(e,t,o){e.exports=o(7645)},9008:function(e,t,o){e.exports=o(3121)},4298:function(e,t,o){e.exports=o(3573)}}]);