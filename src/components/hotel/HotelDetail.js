import { Fragment } from "react";
import { useQuery } from "react-query";
import { useNavigate, useParams } from "react-router-dom";
import apiClient from '../../http-commons';

function HotelDetail(){
    
    // 번호 확인
    const {hno}=useParams()
    // request.getParameter("fno")
    const nav=useNavigate()
    // 목록으로 이동 => history => back() , go(-1)
    const {isLoading, isError, error, data}=useQuery(["hotel-detail", hno], // data 라는 이름으로 받는다.

        async ()=> {
            return await apiClient.get(`/hotel/detail/${hno}`)
        }
    )
    // 서버 연결 지연 상태
    if(isLoading) return <h1 className="text-center">Loading...</h1>
    // 에러 발생
    if(isError) return <h1 className="text-center">{error}</h1>
    console.log(data)

    return (
        <Fragment>
            <div className={"row justify-content-center"} style={{"padding": "2%"}}>
                <h2>호텔 & 숙박</h2>
            </div>
            <hr style={{"width": "65%"}}/>
            <div className={"row justify-content-center"} style={{"paddingBottom": "3%"}}>
                <table className={"table"} style={{"borderCollapse": "collapse", "border": "none", "width": "70%"}}>
                    <tbody>
                    <tr>
                        <td width={"40%"} className={"text-center"} rowSpan={"7"} style={{"borderTop": "none"}}>
                            <img src={data.data.poster}
                                 style={{"width": "500px", "height": "300px", "borderRadius": "25px"}}/>
                        </td>
                        <td colSpan={"2"} style={{"borderTop": "none"}}>
                            <h5><span style={{"color":"#fe5c24"}}>#&nbsp;{data.data.tag}&nbsp;&nbsp;#&nbsp;호텔&nbsp;&nbsp;#&nbsp;숙박</span></h5>
                        </td>
                    </tr>
                    <tr>
                        <h3 style={{
                            "fontSize": "27px",
                            "fontWeight": "bold",
                            "color": "#0c3e72",
                            "padding": "0.5%",
                            "width": "200px"
                        }}>{data.data.name}</h3>
                    </tr>
                    <tr>
                        <th width={"15%"} style={{"borderTop": "none"}}>소재지</th>
                        <td width={"55%"} style={{"borderTop": "none"}}>{data.data.address}</td>
                    </tr>
                    <tr>
                        <th width={"15%"} style={{"borderTop": "none"}}>문의처</th>
                        <td width={"55%"} style={{"borderTop": "none"}}>{data.data.phone}</td>
                    </tr>
                    <tr>
                        <th width={"15%"} style={{"borderTop": "none"}}>홈페이지</th>
                        <td width={"55%"} style={{"borderTop": "none"}}>{data.data.homepage}</td>
                    </tr>
                    </tbody>
                </table>
                <hr style={{"width": "65%"}}/>
            </div>
            <div className={"row justify-content-center"} style={{"paddingBottom": "5%"}}>
                <button className={"btn btn-sm btn-primary"} id={"allBtn"}
                        onClick={() => nav(-1)}>&nbsp;목 록&nbsp;
                </button>
            </div>
        </Fragment>
    )
}
export default HotelDetail