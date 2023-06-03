const cart = document.getElementById("cart");
const popup = document.querySelector(".popup");
const cart_close_btn = document.querySelector(".close_btn");

function popup_visible(){
    //popup.classList.add("popup_visible");
    popup.classList.toggle("popup_visible");
};

cart.addEventListener("click",popup_visible);
cart_close_btn.addEventListener("click",popup_visible);