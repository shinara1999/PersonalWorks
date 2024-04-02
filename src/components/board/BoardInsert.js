import { Fragment, useState, useRef } from "react";
import { useMutation } from "react-query";
import apiClient from '../../http-commons';
// useMutation : 특정 데이터 처리 => 쓰기 => insert, update, delete
import { useNavigate } from "react-router-dom";

function BoardInsert(){

    const nav=useNavigate()
    const nameRef=useRef(null)
    const subRef=useRef(null)
    const contRef=useRef(null)
    const pwdRef=useRef(null)

    const[name, setName]=useState('')
    const[subject, setSubject]=useState('')
    const[content, setContent]=useState('')
    const[pwd, setPwd]=useState('')

    const [result, setResult]=useState(null)
    const {isLoading, mutate:freeboardInsert}=useMutation(
        // freeboardInsert를 호출하면 async() 내의 3개의 함수가 호출된다.
        // 함수 3개
        async ()=> {
            return await apiClient.post(`/board/insert`,{
                // 키(변수명):값(실제 얻어온)
                name:name,
                subject:subject,
                content:content,
                pwd:pwd
            })
        },
        {
            onSuccess:(res)=>{
                const resData={
                    status:res.status,
                    headers:res.headers,
                    data:res.data
                }
                setResult(resData) // 이동
                if(res.data.msg==="yes")
                {
                    window.location.href="/board/list"
                }
                else
                {
                    alert("게시물 추가 오류 발생!!!")
                }
            }
        },
        {
            onError:(err)=>{
                setResult(err.response) // 오류발생
            }
        }
    )

    const boardInsert=()=>{
        if(name.trim()==='')
        {
            nameRef.current.focus()
            return
        }
        else if(subject.trim()==='')
        {
            subRef.current.focus()
            return
        }
        else if(content.trim()==='')
        {
            contRef.current.focus()
            return
        }
        else if(pwd.trim()==='')
        {
            pwdRef.current.focus()
            return
        }
        
        freeboardInsert() // 여기서 호출시켜야함

    }
    if(isLoading) return <h1 className="text-center">Loading...</h1>

    return (
        <Fragment>
            <div className={"row justify-content-center"} style={{"padding": "2%"}}>
                <h2>새 글 작성</h2>
            </div>
        <div className={"row"} style={{"width":"65%","margin":"0px auto"}}>

            <table className={"table"}>
                <tbody>
                <tr>
                    <td width={"15%"} className={"text-center"}>이름</td>
                    <td width={"85%"}>
                        <input type={"text"} size={"15"} className={"input-sm"} onChange={(e)=>setName(e.target.value)} value={name} ref={nameRef} style={{"borderRadius":"20px"}}/>
                    </td>
                </tr>
                <tr>
                    <td width={"15%"} className={"text-center"}>제목</td>
                    <td width={"85%"}>
                        <input type={"text"} size={"50"} className={"input-sm"} onChange={(e)=>setSubject(e.target.value)} value={subject} ref={subRef} style={{"borderRadius":"20px"}}/>
                    </td>
                </tr>
                <tr>
                    <td width={"15%"} className={"text-center"}>내용</td>
                    <td width={"85%"}>
                        <textarea rows={"10"} cols={"53"} onChange={(e)=>setContent(e.target.value)} ref={contRef} style={{"borderRadius":"20px"}}>{content}</textarea>
                    </td>
                </tr>
                <tr>
                    <td width={"15%"} className={"text-center"}>비밀번호</td>
                    <td width={"85%"}>
                        <input type={"password"} size={"15"} className={"input-sm"} onChange={(e)=>setPwd(e.target.value)} value={pwd} ref={pwdRef} style={{"borderRadius":"20px"}}/>
                    </td>
                </tr>
                <tr>
                    <td colSpan={"2"} className={"text-center"}>
                        <input type={"button"} className={"btn btn-sm btn-info"} value={"글쓰기"} onClick={boardInsert} id={"allBtn"}/>&nbsp;
                        <input type={"button"} className={"btn btn-sm btn-warning"} value={"취소"} onClick={()=>nav(-1)} id={"allBtn"}/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        </Fragment>
    )

}
export default BoardInsert