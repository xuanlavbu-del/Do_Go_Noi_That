<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <a class="navbar-brand"
       href="<%=request.getContextPath()%>/dashboard">

        ĐỒ GỖ NỘI THẤT ADMIN

    </a>

    <div class="ml-auto text-white">

        Xin chào,

        <strong>

            <%=session.getAttribute("hoTen")%>

        </strong>

    </div>

</nav>