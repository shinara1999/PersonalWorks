import { Fragment, useEffect, useState } from "react";
import { useQuery } from "react-query";
import apiClient from '../../http-commons';
import { Link } from "react-router-dom";
import Pagination from "react-js-pagination";

function BoardList(){
    const [curpage, setCurpage]=useState(1)
    const {isLoading, isError, error, data, refetch:hitIncrement}=useQuery(["board-list", curpage],
    
        async ()=> {
            return await apiClient.get(`/board/list/${curpage}`)
        }
    )
    useEffect(()=>{
        hitIncrement()
    },[isLoading])
    if(isLoading) return <h1 className="text-center">Loading...</h1>
    if(isError) return <h1 className="text-center">{error}</h1>
    
    console.log(data)

    const handleChange=(page)=>{
        setCurpage(page)
    }
    
    return (
        <Fragment>
            <div className={"row justify-content-center"} style={{"padding": "2%"}}>
                <h2>게시판</h2>
            </div>
            <div className={"row"} style={{"width":"65%","margin":"0px auto"}}>
                
                <table className={"table"} width={"65%"}>
                    <tbody>
                    <tr>
                        <td>
                            <Link to={"/board/insert"} className={"btn btn-sm btn-success"} id={"allBtn"}>새 글</Link>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <table className={"table"}>
                    <thead>
                    <tr className={"success"}>
                        <th className={"text-center"} width={"10%"}>번호</th>
                        <th className={"text-center"} width={"40%"}>제목</th>
                        <th className={"text-center"} width={"15%"}>이름</th>
                        <th className={"text-center"} width={"20%"}>작성일</th>
                        <th className={"text-center"} width={"15%"}>조회수</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        data.data.bList &&
                        data.data.bList.map((board)=>
                            <tr>
                                <td className={"text-center"} width={"10%"}>{board.no}</td>
                                <td width={"40%"}>
                                    <Link to={"/board/detail/"+board.no}>
                                        {board.subject}
                                    </Link>
                                </td>
                                <td className={"text-center"} width={"15%"}>{board.name}</td>
                                <td className={"text-center"} width={"20%"}>{board.regdate.substring(0, board.regdate.indexOf(" "))}</td>
                                <td className={"text-center"} width={"15%"}>{board.hit}</td>
                            </tr>
                        )
                    }
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colSpan={"5"} className={"text-center"}>
                            <input type={"button"} className={"btn-sm btn-danger"} value={"이전"} id={"allBtn"} />
                            &nbsp;{curpage} page / {data.data.totalpage} pages&nbsp;
                            <input type={"button"} className={"btn-sm btn-danger"} value={"다음"} id={"allBtn"} />
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </Fragment>

    )
}
export default BoardList