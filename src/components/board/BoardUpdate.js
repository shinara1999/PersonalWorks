import { Fragment, useRef, useState, useEffect } from "react";
import { useQuery, useMutation } from "react-query";
import apiClient from '../../http-commons';
import { useParams, useNavigate } from "react-router-dom";

function BoardUpdate(){
    const {no} = useParams()
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

    // 수정데이터 읽기
    const {isLoading, isError, error, data}=useQuery(['board-update', no],
        
        async ()=> {
            return await apiClient.get(`/board/update/${no}`)
        },
        {
            onSuccess:(res)=>{
                setName(res.data.name)
                setSubject(res.data.subject)
                setContent(res.data.content)
            }
        },
        {
            onError:(err)=>{
                console.log(err.response)
            }
        }
    )
    
    // 수정
    const {mutate:boardUpdate}=useMutation(
        async () => {
            return await apiClient.put(`/board/update_ok/${no}`,{
                name:name,
                subject:subject,
                content:content,
                pwd:pwd
            })
        },
        {
            onSuccess:(res)=>{
                if(res.data.msg==="yes")
                {
                    window.location.href="/board/detail/"+no
                }
                else
                {
                    alert("비밀번호 오류")
                    setPwd('')
                    pwdRef.current.focus()
                }
            }
        },
        {
            onError:(err)=>{
                console.log(err.response) // 오류발생
            }
        }

    )

    if(isLoading) return <h1 className="text-center">Loading...</h1>
    if(isError) return <h1 className="text-center">{error}</h1>

    const boardUpdateOk=()=>{
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
        boardUpdate()
    }
    
    return (
        <Fragment>
            <div className={"row justify-content-center"} style={{"padding": "2%"}}>
                <h2>수정하기</h2>
            </div>
        <div className={"row"} style={{"width":"65%","margin":"0px auto"}}>
        
            <table className={"table"}>
                <tbody>
                    `<tr>
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
                            <textarea rows={"10"} cols={"53"} onChange={(e)=>setContent(e.target.value)} ref={contRef} value={content} style={{"borderRadius":"20px"}}></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td width={"15%"} className={"text-center"}>비밀번호</td>
                        <td width={"85%"}>
                            <input type={"password"} size={"15"} className={"input-sm"} onChange={(e)=>setPwd(e.target.value)} ref={pwdRef} value={pwd} style={{"borderRadius":"20px"}}/>
                        </td>
                    </tr>
                    <tr>
                        <td colSpan={"2"} className={"text-center"}>
                            <input type={"button"} className={"btn btn-sm btn-info"} value={"수정"} onClick={boardUpdateOk} id={"allBtn"}/>
                            <input type={"button"} className={"btn btn-sm btn-warning"} value={"취소"} onClick={()=>nav(-1)} id={"allBtn"}/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        </Fragment>
    )
}
export default BoardUpdate