<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set scope="page" var="path" value="${pageContext.request.contextPath}"/>
    <title>emplist</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${path}/css/style.css"/>
    <link href="${path}/css/common.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${path}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        function beforeDelete(e) {
            var f = confirm("确定要删除员工?");
            if (f) {
                location.href = '${path}/emp/delete?id=' + e;
            }

        }
    </script>
</head>
<body>
<div id="wrap">
    <div id="top_content">
        <div id="header">
            <div id="rightheader">
                <p>
                    2009/11/20
                    <br/>
                </p>
            </div>
            <div id="topheader">
                <h1 id="title">
                    <a href="#">main</a>
                </h1>
            </div>
            <div id="navigation">
            </div>
        </div>
        <div id="content">
            <p id="whereami">
            </p>
            <h1>
                Welcome ${user.name}!
            </h1>
            <p>
                <input type="button" class="button" value="Add Employee" onclick="location='${path}/emp/addEmp.jsp'"/>
            </p>
        </div>
    </div>
    <div id="footer">
        <table class="table">
            <tr class="table_header">
                <td>
                    ID
                </td>
                <td>
                    Name
                </td>
                <td>
                    Salary
                </td>
                <td>
                    Age
                </td>
                <td>
                    Bir
                </td>
                <td>
                    Dept
                </td>
                <td>
                    Operation(处理删除的友情提醒)
                </td>
            </tr>
            <c:forEach items="${emps}" var="emp" varStatus="stu">
                <tr class="row${(stu.count mod 2)==0?2:1}">
                    <td>
                            ${emp.id}
                    </td>
                    <td>
                            ${emp.name}
                    </td>
                    <td>
                            ${emp.salary}
                    </td>
                    <td>
                            ${emp.age}
                    </td>
                    <td>
                        <fmt:formatDate value="${emp.bir}"/>
                    </td>
                    <td>
                            ${emp.deptName}
                    </td>
                    <td>
                        <a href="javascript:void(0);" onclick="beforeDelete('${emp.id}');">delete emp</a>&nbsp;<a
                            href="${path}/emp/getOne?id=${emp.id}">update emp</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <form action="${path}/emp/empList" method="post">
            <div class="pagination">
                <c:if test="${currentPage gt 1}">
                    <span class="firstPage"><a href="${path}/emp/empList?deptId=${emps[0].deptId}&page=1"></a></span>
                    <span class="previousPage"><a
                            href="${path}/emp/empList?deptId=${emps[0].deptId}&page=${currentPage gt 1?currentPage-1:1}"></a></span>
                </c:if>
                <c:if test="${currentPage gt 3}">
                    <span class="pageBreak">...</span>
                </c:if>
                <c:if test="${currentPage gt 1}">
                    <c:forEach begin="${(currentPage-2) le 0?1:(currentPage-2)}" var="page"
                               end="${(currentPage-1) le 0?1:(currentPage-1)}">
                        <c:if test="${page ge 1}">
                            <a href="${path}/emp/empList?deptId=${emps[0].deptId}&page=${page}">${page}</a>
                        </c:if>
                    </c:forEach>
                </c:if>
                <span class="currentPage">${currentPage}</span>
                <c:forEach begin="${currentPage+1}" var="page" end="${currentPage+2}">
                    <c:if test="${page le totalPage}">
                        <a href="${path}/emp/empList?deptId=${emps[0].deptId}&page=${page}">${page}</a>
                    </c:if>
                </c:forEach>
                <c:if test="${(currentPage+2) lt totalPage }">
                    <span class="pageBreak">...</span>
                </c:if>
                <%--<a
                    href="javascript:$.pageSkip(2);">2</a> <a
                    href="javascript:$.pageSkip(3);">3</a>--%> <%--<span class="pageBreak">...</span>--%>
                <c:if test="${currentPage lt totalPage}">
                    <a class="nextPage"
                       href="${path}/emp/empList?deptId=${emps[0].deptId}&page=${currentPage lt totalPage?currentPage+1:totalPage}">&nbsp;</a>
                    <a class="lastPage"
                       href="${path}/emp/empList?deptId=${emps[0].deptId}&page=${totalPage}">&nbsp;</a>
                </c:if>


                <input name="deptId" value="${emps[0].deptId}" hidden/>
                <span class="pageSkip"> 共${totalPage}页 到第<input id="pageNumber"
                                                                name="page" value="${currentPage}"
                                                                maxlength="${totalPage}"
                                                                onpaste="return false;">页
								<button type="submit">&nbsp;</button>
						</span>
            </div>
        </form>

        <div id="footer_bg">
            ABC@126.com
        </div>
    </div>
</div>
</body>
</html>
