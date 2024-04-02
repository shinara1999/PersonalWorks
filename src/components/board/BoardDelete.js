import { useNavigate, useParams } from "react-router-dom";
import { useMutation } from "react-query";
import apiClient from '../../http-commons';
import { useState, useRef, Fragment } from "react";

function BoardDelete(){
    const {no} = useParams()
    const nav = useNavigate()
    const pwdRef=useRef(null)
    const [pwd, setPwd]=useState('') 
    const {isLoading, mutate:boardDelete}=useMutation( // mutate : 함수명 지정
        async () => {
            return await apiClient.delete(`/board/delete/${no}/${pwd}`) // 고유번호, 비밀번호를 넘긴다.
        },
        {
            onSuccess:(res)=>{
                if(res.data.msg==='yes')
                {
                    window.location.href='/board/list'
                }
                else
                {
                    alert("비밀번호 오류")
                    setPwd('') // pwd는 상수기 때문에 공백을 줄 수 없다. setPwd 라고 써야함.
                    pwdRef.current.focus()
                }
            }
        },
        {
            onError:(err)=>{
                console.log(err.response)
            }
        }
    )

    const boardDeleteOk=()=>{
        if(pwd.trim()==="")
        {
            pwdRef.current.focus()
            return
        }
        boardDelete() // mutate에서 지정한 함수를 호출한다.
    }

    return (
        <Fragment>
            <div className={"row justify-content-center"} style={{"padding": "2%"}}>
                <h2>삭제하기</h2>
            </div>
        <div className={"row row1"} style={{"width":"65%","margin":"0px auto"}}>
            <table className={"table"}>
                <tbody>
                    <tr>
                        <td>
                            비밀번호:&nbsp;<input type={"password"} className={"input-sm"} ref={pwdRef} value={pwd} onChange={(e)=>setPwd(e.target.value)} style={{"borderRadius":"30px"}}/>
                        </td>
                    </tr>
                    <tr>
                        <td className={"text-center"}>
                            <input type={"button"} value={"삭제"} className={"btn-sm btn-danger"} onClick={boardDeleteOk} id={"allBtn"}/>
                            <button className={"btn btn-sm btn-danger"} onClick={()=>nav(-1)} id={"allBtn"}>취소</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        </Fragment>
    )

}
export default BoardDelete