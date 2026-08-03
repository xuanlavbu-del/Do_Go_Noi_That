// =====================================
// MENU MOBILE
// =====================================


document.addEventListener(
    "DOMContentLoaded",
    function(){


        console.log(
            "DoGoNoiThat loaded"
        );



    });






// =====================================
// XÁC NHẬN XÓA
// =====================================


function xacNhanXoa(){


    return confirm(
        "Bạn có chắc muốn xóa sản phẩm này?"
    );


}







// =====================================
// PREVIEW ẢNH ADMIN
// =====================================


function xemTruocAnh(input){


    let img =
        document.getElementById(
            "preview"
        );



    if(input.files &&
        input.files[0]){


        let reader =
            new FileReader();



        reader.onload =
            function(e){


                img.src =
                    e.target.result;


            }



        reader.readAsDataURL(
            input.files[0]
        );


    }


}








// =====================================
// TĂNG GIẢM SỐ LƯỢNG
// =====================================


function tangSoLuong(id){


    let input =
        document.getElementById(id);



    input.value =
        parseInt(input.value)+1;



}





function giamSoLuong(id){


    let input =
        document.getElementById(id);



    if(parseInt(input.value)>1){


        input.value =
            parseInt(input.value)-1;


    }


}









// =====================================
// FORMAT TIỀN VIỆT NAM
// =====================================


function dinhDangTien(gia){


    return gia.toLocaleString(
            "vi-VN"
        )
        +" VNĐ";


}







// =====================================
// SCROLL TOP
// =====================================


let btnTop =
    document.createElement(
        "button"
    );



btnTop.id =
    "btnTop";


btnTop.innerHTML =
    "↑";


document.body.appendChild(
    btnTop
);





window.onscroll =
    function(){


        if(
            document.documentElement.scrollTop
            >300
        ){


            btnTop.style.display =
                "block";


        }

        else{


            btnTop.style.display =
                "none";


        }


    }





btnTop.onclick =
    function(){


        window.scrollTo({

            top:0,

            behavior:"smooth"

        });


    }








// =====================================
// VALIDATE ĐĂNG KÝ
// =====================================


function kiemTraDangKy(){


    let mk =
        document.querySelector(
            "input[name='matKhau']"
        ).value;



    let xacNhan =
        document.querySelector(
            "input[name='xacNhanMatKhau']"
        ).value;




    if(mk!==xacNhan){


        alert(
            "Mật khẩu không trùng nhau"
        );


        return false;


    }



    return true;


}