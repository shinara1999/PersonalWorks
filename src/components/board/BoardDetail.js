import { useQuery } from "react-query";
import apiClient from '../../http-commons';
import { Link, useParams } from "react-router-dom";
import { Fragment } from "react";

function BoardDetail(){
    const {no}=useParams()

    const {isLoading, isError, error, data}=useQuery(["board-detail", no],
    
        async ()=> {
            return await apiClient.get(`/board/detail/${no}`)
        }
    )
    if(isLoading) return <h1 className="text-center">Loading...</h1>
    if(isError) return <h1 className="text-center">{error}</h1>
    console.log(data)

    return (
        <Fragment>
            <div className={"row justify-content-center"} style={{"padding": "2%"}}>
                <h2>내용 보기</h2>
            </div>
        <div className={"row"} style={{"width":"65%","margin":"0px auto"}}>

            <table className={"table"}>
                <tbody>
                <tr>
                    <td className={"text-center success"} width={"20%"}>번호</td>
                    <td className={"text-center"} width={"30%"}>{data.data.no}</td>
                    <td className={"text-center success"} width={"20%"}>작성일</td>
                    <td className={"text-center"} width={"30%"}>{data.data.regdate}</td>
                </tr>
                <tr>
                    <td className={"text-center success"} width={"20%"}>이름</td>
                    <td className={"text-center"} width={"30%"}>{data.data.name}</td>
                    <td className={"text-center success"} width={"20%"}>조회수</td>
                    <td className={"text-center"} width={"30%"}>{data.data.hit}</td>
                </tr>
                <tr>
                    <td className={"text-center success"} width={"20%"}>제목</td>
                    <td colSpan={"3"}>{data.data.subject}</td>
                </tr>
                <tr>
                    <td className={"text-left"} height={"200"} colSpan={"4"} valign={"top"}>
                        <pre style={{"whiteSpace":"pre-wrap", "backgroundColor":"white", "border":"none"}}>{data.data.content}</pre>
                    </td>
                </tr>
                <tr>
                    <td className={"text-right"} colSpan={"4"}>
                        <Link to={"/board/update/"+data.data.no} className={"btn-info btn-sm"} id={"allBtn"}>수정</Link>&nbsp;
                        <Link to={"/board/delete/"+data.data.no} className={"btn-info btn-sm"} id={"allBtn"}>삭제</Link>&nbsp;
                        <Link to={"/board/list"} className={"btn-info btn-sm"} id={"allBtn"}>목록</Link>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        </Fragment>
    )

}
export default BoardDetail