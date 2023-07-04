const cart = document.getElementById("cart");
const popup = document.querySelector(".popup");
const cart_close_btn = document.querySelector(".close_btn");
const popup_body_container = document.querySelector(".popup_body_container");
const cart_num = document.querySelector(".cart__num");
const sbm_btn_design = document.querySelector(".sbm_btn_design");
const frm = document.getElementById("kebabs_id");

function popup_visible(){
    //popup.classList.add("popup_visible");
    popup.classList.toggle("popup_visible");
};

function getJSONData() {
    return new Promise(async function(resolve,reject){
        try{
            const response = await fetch("http://localhost:8080/design/getKebabOrder");
            const json = await response.json();
            console.log("JSON!!! ",json);
            resolve(json);
        }catch(error){
            console.log("ERROR!!! ", error);
            reject(error);
        }
    });
}

function createKebabs(kebab){
    let div = document.createElement("div");
    div.className = "popup_body_item_container"
    let h3 = document.createElement("h3");
    h3.innerText = kebab.name;
    div.append(h3);
    let h4 = document.createElement("h4");
    h4.innerText = "ingradients";
    div.append(h4);

        kebab.ingredients.forEach(element => {
            let p = document.createElement("p");
            p.textContent = element.type + " : " +
            element.name + "\n";
            div.append(p);
        });
    return div;
}

function addKebabs(){
    return new Promise(async function(resolve,reject){

     let order;
     await getJSONData().then(json =>{
     order = json},error=>{order = error});

     console.log("order data: \n" + order);

         for(let i=0;i < order.kebabs.length;++i){
             //console.log(orderObj.kebabs[i].name)
             let div = createKebabs(order.kebabs[i]);
             popup_body_container.append(div);
         }
    });
}

async function countItem(){
    let order;
    await getJSONData().then(json =>{
    order = json},error=>{order = error});

    if(order !==null || order !== undefined){
        cart_num.textContent = order.kebabs.length;
        console.log("call countItem " + order.kebabs.length);
        console.log("write c_0_" + cart_num.textContent);
    }else{
        console.log("write c_1_" + cart_num.textContent);
    }
}

function checkForm() {
   let uncheck = frm.querySelectorAll('input[type="checkbox"]');

   for(let i=0;i<uncheck.length;i++){

     if(uncheck[i].type=='checkbox'){
      uncheck[i].checked=false;
     }

    }
}
checkForm();
countItem();

cart.addEventListener("click",popup_visible);
cart_close_btn.addEventListener("click",popup_visible);
cart.addEventListener("click",addKebabs);

/*sbm_btn_design.addEventListener("click",function(){
frm.reset()});*/
/*frm.addEventListener("submit",function(){
console.log("submit")})*/


