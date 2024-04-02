import { Fragment , useState, useEffect} from "react";
import { useQuery, useMutation } from "react-query";
import apiClient from '../../http-commons';
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import Pagination from "react-js-pagination";

function HotelFind(){

    const [curpage, setCurpage]=useState(1)
    const [address, setAddress]=useState('양화로')
    const {isLoading, isError, error, data, refetch:hotelFindData}=useQuery(["hotel-find", curpage],
    
        async ()=> {
            return await apiClient.get(`/hotel/find/${curpage}/${address}`)
        }
    )
    // 서버 연결 지연 상태
    if(isLoading) return <h1 className="text-center">Loading...</h1>
    // 에러 발생
    if(isError) return <h1 className="text-center">{error}</h1>
    console.log(data)
    
    const findChange=(e)=>{
        setAddress(e.target.value)
    }
    const pageChange=(page)=>{
        setCurpage(page)
    }
    const findBtn=()=>{
        hotelFindData()
    }
    
    return (
        <Fragment>

            <div className={"row justify-content-center"} style={{"padding": "2%"}}>
                <h2>호텔 검색 결과</h2>
            </div>
            <div className={"row justify-content-center"} style={{"paddingBottom":"2%"}}>
                <input type={"text"} size={"20"} className={"input-sm"} placeholder={"도로명주소 입력"}
                       value={address}
                       onChange={findChange} style={{"borderRadius": "25px"}}
                />
                <input type={"button"} className={"btn btn-sm btn-success"} value={" 검 색 "}
                       onClick={findBtn} id={"allBtn"}
                />
            </div>
            <div style={{"height": "10px"}}></div>
            <div className={"row justify-content-center"} style={{"marginLeft": "11%"}}>

            {data.data.hList &&
                data.data.hList.map((hotel)=>   
                    <div className="col-md-4" style={{"width":"300px"}}>
                        <div className="single_place" style={{"paddingBottom":"10%","width":"300px"}}>
                            <Link to={"/hotel/detail/" + hotel.hno}>
                                <img src={hotel.poster} style={{"width": "300px", "height": "200px", "borderRadius":"25px"}}/>
                                <p style={{"fontSize":"13px","color":"#fe5c24","paddingTop":"0.5%"}}>#&nbsp;{hotel.tag}&nbsp;&nbsp;#&nbsp;호텔 및 숙박</p>
                                <p style={{"fontSize":"20px","fontWeight":"bold","color":"#0c3e72","padding":"0.5%"}}>{hotel.name}</p>
                            </Link>
                        </div>
                    </div>
                )
            }
            </div>
            <div className={"row justify-content-center"} style={{"padding": "3%"}}>
                <div className={"text-center"} style={{"width":"900px"}}>
                    <Pagination
                        activePage={curpage}
                            itemsCountPerPage={9}
                            totalItemsCount={data.data.count}
                            pageRangeDisplayed={3}
                            prevPageText={"<"}
                            nextPageText={">"}
                            onChange={pageChange}
                            itemClass="custom-pagination-item" // 커스텀 클래스 추가
                            linkClass="custom-pagination-link" // 커스텀 클래스 추가
                            activeLinkClass="custom-pagination-active-link" // 커스텀 클래스 추가
                            style={{"display": "flex",
                                "flex-wrap": "nowrap",
                                "justify-content": "center"}}
                        />
                </div>
            </div>
        </Fragment>
    )
}

export default HotelFind