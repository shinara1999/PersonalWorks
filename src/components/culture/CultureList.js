import { Fragment, useState } from "react";
import { useQuery } from "react-query";
import apiClient from '../../http-commons';
import { Link } from "react-router-dom";
import Pagination from "react-js-pagination";
import "../styles/pagination.css";

function CultureList(){
    const [curpage, setCurpage]=useState(1)
    const {isLoading, isError, error, data}=useQuery(["culture-list", curpage],
    
        async ()=> {
            return await apiClient.get(`/culture/list/${curpage}`)
        }
    )
    if(isLoading) return <h1 className="text-center">Loading...</h1>
    if(isError) return <h1 className="text-center">{error}</h1>
    console.log(data)
    
    const handleChange=(page)=>{
        setCurpage(page)
    }
    
    return (
        
        <Fragment>
            <div className={"row justify-content-center"} style={{"padding": "2%"}}>
                <h2>역사와 문화</h2>
            </div>
            <div className={"row justify-content-center"} style={{"marginLeft":"11%"}}>

            {data.data.list &&
                data.data.list.map((culture)=>   
                    <div className="col-md-4" style={{"width":"300px"}}>
                        <div className="single_place" style={{"paddingBottom":"10%","width":"300px"}}>
                            <Link to={"/culture/detail/" + culture.cno}>
                                <img src={culture.poster} style={{"width": "300px", "height": "200px", "borderRadius":"25px"}}/>
                                <p style={{"fontSize":"13px","color":"#fe5c24","paddingTop":"0.5%"}}>#&nbsp;{culture.tag}&nbsp;&nbsp;#&nbsp;역사와 문화</p>
                                <p style={{"fontSize":"20px","fontWeight":"bold","color":"#0c3e72","padding":"0.5%"}}>{culture.name}</p>
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
                            onChange={handleChange}
                            itemClass="custom-pagination-item" // 커스텀 클래스 추가
                            linkClass="custom-pagination-link" // 커스텀 클래스 추가
                            activeLinkClass="custom-pagination-active-link" // 커스텀 클래스 추가
                        />
                </div>
            </div>
        </Fragment>
    )
}
export default CultureList