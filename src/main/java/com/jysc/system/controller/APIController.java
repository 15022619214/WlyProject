package com.jysc.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jysc.system.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("/api")
public class APIController extends BaseController{

    @ResponseBody
    @RequestMapping("/getIsOpenPlots")
    public JSONObject getIsOpenPlots(@RequestParam("id") int id){
        JSONArray arry = new JSONArray();
        JSONObject json = new JSONObject();
        Plotsobjects byIsOpen = this.plotsobjectsService.findById(id);
        List<Plotsclassify> pclist = this.plotsclassifyService.findByPoid(byIsOpen.getId());
        json.put("Plotsobjects_name",byIsOpen.getPoname());
        json.put("Plotsobjects_imguel",byIsOpen.getPoimgurl());
        json.put("Plotsobjects_logo",byIsOpen.getPo_logo_imgurl());
        if(this.securityUtil.getUserinfo()!=null){
            int userid = this.securityUtil.getUserinfo().getId();
            Set<String> roles = AuthorityUtils.authorityListToSet(this.securityUtil.getUserinfo().getAuthorities());
            if(roles.contains("管理员")){
                if(pclist.size() > 0){
                    for (int i = 0; i < pclist.size(); i++) {
                        JSONObject json_info = new JSONObject();
                        json_info.put("Plotsclassify_id",pclist.get(i).getId());
                        json_info.put("Plotsclassify_name",pclist.get(i).getPcname());
                        List<Plots> plots = this.plotsService.findByPcid(pclist.get(i).getId());
                        json_info.put("Plots_list", plots);
                        arry.add(i,json_info);
                    }
                }
            }else if(roles.contains("普通用户")){
                if(pclist.size() > 0){
                    for (int i = 0; i < pclist.size(); i++) {
                        JSONObject json_info = new JSONObject();
                        json_info.put("Plotsclassify_id",pclist.get(i).getId());
                        json_info.put("Plotsclassify_name",pclist.get(i).getPcname());
                        List<Plots> plots = this.plotsService.findByPcidAndUserid(pclist.get(i).getId(), userid);
                        json_info.put("Plots_list", plots);
                        arry.add(i,json_info);
                    }
                }
            }else if(roles.contains("分享")){
                if(pclist.size() > 0){
                    for (int i = 0; i < pclist.size(); i++) {
                        JSONObject json_info = new JSONObject();
                        json_info.put("Plotsclassify_id",pclist.get(i).getId());
                        json_info.put("Plotsclassify_name",pclist.get(i).getPcname());
                        List<Plots> plots = this.plotsService.findByPcid(pclist.get(i).getId());
                        json_info.put("Plots_list", plots);
                        arry.add(i,json_info);
                    }
                }
            }
        }else{
            if(pclist.size() > 0){
                for (int i = 0; i < pclist.size(); i++) {
                    JSONObject json_info = new JSONObject();
                    json_info.put("Plotsclassify_id",pclist.get(i).getId());
                    json_info.put("Plotsclassify_name",pclist.get(i).getPcname());
                    List<Plots> plots  = this.plotsService.findByPcid(pclist.get(i).getId());
                    json_info.put("Plots_list", plots);
                    arry.add(i,json_info);
                }
            }

        }
        json.put("Plotsobjects_info",arry);

        return json;
    }

    @ResponseBody
    @RequestMapping("/getAllplotsisopen")
    public JSONObject getAllplotsisopen(){
        JSONObject json = new JSONObject();
        List<Plotsobjects> all = this.plotsobjectsService.findByIsOpen(1);
        json.put("Plotsobjects_info",all);
        return json;
    }

    @ResponseBody
    @RequestMapping("/getAllplots")
    public JSONObject getAllplots(@RequestParam("name") String name){
        JSONObject json = new JSONObject();
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("LIKE_poname", name);
        List<Plotsobjects> all = this.plotsobjectsService.query(maps);
        json.put("Plotsobjects_info",all);
        return json;
    }
    
    //项目操作
    @ResponseBody
    @RequestMapping("/savePlotsobject")
    public JSONObject savePlotsobject(@RequestParam("name") String name,
    		@RequestParam("imgurl") String imgurl,
             @RequestParam("imgurllogo") String imgurllogo) {
    	JSONObject json = new JSONObject();
    	
    	List<Plotsobjects> list = this.plotsobjectsService.findAll();
    	
    	Plotsobjects pl = new Plotsobjects();
    	if(list.size() == 0) {
    		pl.setIsOpen(1);
    	}else {
    		pl.setIsOpen(0);
    	}   	
    	pl.setPoname(name);
    	pl.setPoimgurl(imgurl);
    	pl.setPo_logo_imgurl(imgurllogo);
    	this.plotsobjectsService.save(pl);
    	
    	json.put("info", "success");
    	
    	return json;
    }
    
    @ResponseBody
    @RequestMapping("/editPlotsobject")
    public JSONObject editPlotsobject(@RequestParam("id") int id,
    		@RequestParam("name") String name,
    		@RequestParam("imgurl") String imgurl,
                                      @RequestParam("imgurllogo") String imgurllogo) {
    	JSONObject json = new JSONObject();
    	
    	Plotsobjects pl = this.plotsobjectsService.findById(id);
    	pl.setPoname(name);
    	pl.setPoimgurl(imgurl);
        pl.setPo_logo_imgurl(imgurllogo);
    	this.plotsobjectsService.save(pl);
    	
    	json.put("info", "success");
    	
    	return json;
    }
    
    @ResponseBody
    @RequestMapping("/isopens")
    public JSONObject isopens(@RequestParam("id") int id,
    		@RequestParam("type") int type) {
    	JSONObject json = new JSONObject();
    	
    	List<Plotsobjects> list = this.plotsobjectsService.findAll();
    	if(type == 0) {
    		//禁用
    		if(list.size() == 1) {
    			json.put("info", "isOne");
    		}else {
    			List<Plotsobjects> plotsobjects = this.plotsobjectsService.findByIsOpen(1);
    			if(plotsobjects.size() == 1) {
    				json.put("info", "noclose");
    			}else {
    				Plotsobjects pl = this.plotsobjectsService.findById(id);
                	pl.setIsOpen(type);
                	this.plotsobjectsService.save(pl);            	
                	json.put("info", "close");
    			}   			
    		}
    	}else if(type == 1) {
    		List<Plotsclassify> plotsclassifies = this.plotsclassifyService.findByPoid(id);
    		if(plotsclassifies.size() == 0) {
    			json.put("info", "noopen");
    		}else {
    			Plotsobjects pl = this.plotsobjectsService.findById(id);
            	pl.setIsOpen(type);
            	this.plotsobjectsService.save(pl);
            	json.put("info", "open");
    		}    		       	
    	}
    	    	
    	return json;
    }
    
    @ResponseBody
    @RequestMapping("/trash")
    public JSONObject trash(@RequestParam("id") int id) {
    	JSONObject json = new JSONObject();
    	List<Plotsclassify> plotsclassifies = this.plotsclassifyService.findByPoid(id);
    	if(plotsclassifies.size() > 0) {
    		json.put("info", "hasFall");
    	}else {
    		this.plotsobjectsService.del(id);
    		json.put("info", "success");
    	}
    	    	
    	return json;
    }
    
    @ResponseBody
    @RequestMapping("/trashAll")
    public JSONObject trashAll(@RequestParam("id") int id) {
    	JSONObject json = new JSONObject();
    		
    	List<Plotsclassify> plotsclassifies= this.plotsclassifyService.findByPoid(id);
    	for (int i = 0; i < plotsclassifies.size(); i++) {
    		List<Plots> plots = this.plotsService.findByPcid(plotsclassifies.get(i).getId());
    		for (int j = 0; j < plots.size(); j++) {
    			//删除info
    			Plotsinfo plotsinfos = this.plotsinfoService.findByPlotsid(plots.get(j).getId());
    			if(plotsinfos!=null) {

					//删除全景
					List<Plotsinfomap> map = this.plotsinfomapService.findByInfoid(plotsinfos.getId());
					if(map.size() > 0){
						for (int x = 0; x < map.size(); x++) {
							this.plotsinfomapService.del(map.get(x).getId());
						}
					}
					//删除介绍图
					List<Plotsinfoimg> img = this.plotsinfoimgService.findByInfoid(plotsinfos.getId());
					if(img.size() > 0){
						for (int x = 0; x < img.size(); x++) {
							this.plotsinfoimgService.del(img.get(x).getId());
						}
					}

    				this.plotsinfoService.del(plotsinfos.getId());
    			}

    			//删除底部导航
                Plotsinfonav nav = this.plotsinfonavService.findByPlotsid(plots.get(j).getId());
                if(nav != null){
                    this.plotsinfonavService.del(nav.getId());
                }

                //删除服务
                List<Plotsinfoservice> service = this.plotsinfoserviceService.findByPlotsid(plots.get(j).getId());
                if(service.size() > 0){
                    for (int x = 0; x < service.size(); x++) {
                        this.plotsinfoserviceService.del(service.get(x).getId());
                    }
                }

    			this.plotsService.del(plots.get(j).getId());
			}
    		this.plotsclassifyService.del(plotsclassifies.get(i).getId());
		}
    	
    	this.plotsobjectsService.del(id);
    	json.put("info", "success");   	
    	    	
    	return json;
    }
    
    //地块信息、分类操作
    @ResponseBody
    @RequestMapping("/getplotsClassifyBypoid")
    public JSONObject getplotsClassifyBypoid(@RequestParam("id") int id) {
    	JSONObject json = new JSONObject();
    	
    	List<Plotsclassify> pclist = this.plotsclassifyService.findByPoid(id);
    	json.put("plotsClassify", pclist);
    	
    	return json;
    }
    
    //分类操作
    @ResponseBody
    @RequestMapping("/saveplotsClassify")
    public JSONObject saveplotsClassify(@RequestParam("poid") int poid,
    		@RequestParam("pcname") String pcname) {
    	JSONObject json = new JSONObject();
        Plotsclassify ishave = this.plotsclassifyService.findByPcname(pcname);
        if(ishave == null){
            Plotsclassify plotsclassify = new Plotsclassify();
            plotsclassify.setPcname(pcname);
            plotsclassify.setPoid(poid);
            this.plotsclassifyService.save(plotsclassify);
            json.put("info", "success");
        }else{
            json.put("info", "error");
        }
    	return json;
    }
    
    @ResponseBody
    @RequestMapping("/editplotsClassify")
    public JSONObject editplotsClassify(@RequestParam("id") int id,
    		@RequestParam("pcname") String pcname) {
    	JSONObject json = new JSONObject();
    	
    	Plotsclassify plotsclassify = this.plotsclassifyService.findById(id);
    	plotsclassify.setPcname(pcname);
    	this.plotsclassifyService.save(plotsclassify);
    	json.put("info", "success");
    	
    	return json;
    }
    
    @ResponseBody
    @RequestMapping("/trashpc")
    public JSONObject trashpc(@RequestParam("id") int id) {
    	JSONObject json = new JSONObject();
    	
    	List<Plots> list = this.plotsService.findByPcid(id);
    	if(list.size() > 0) {
    		json.put("info", "error");
    	}else {
    		this.plotsclassifyService.del(id);
    		json.put("info", "success");
    	}
    	
    	return json;
    }
    
    
    @ResponseBody
    @RequestMapping("/getplotsBypcid")
    public JSONObject getplotsBypcid(@RequestParam("id") int id) {
    	JSONObject json = new JSONObject();
    	
    	List<Plots> plots = this.plotsService.findByPcid(id);
    	json.put("plots", plots);
    	
    	return json;
    }
    
    
    //地块操作
    @ResponseBody
    @RequestMapping("/savePlots")
    public JSONObject savePlots(@RequestParam("pcid") int pcid,
    		@RequestParam("plname") String plname) {
    	JSONObject json = new JSONObject();

    	int userid = this.securityUtil.getUserinfo().getId();

    	Plots plots = new Plots();
    	plots.setPlots(plname);
    	plots.setPcid(pcid);
    	plots.setUserid(userid);
    	this.plotsService.save(plots);
    	
    	json.put("info", "success");
    	
    	return json;
    }
    
    @ResponseBody
    @RequestMapping("/editPlots")
    public JSONObject editPlots(@RequestParam("id") int id,
    		@RequestParam("plname") String plname) {
    	JSONObject json = new JSONObject();
    	
    	Plots plots = this.plotsService.findById(id);
    	plots.setPlots(plname);
    	this.plotsService.save(plots);
    	json.put("info", "success");
    	
    	return json;
    }
    
    @ResponseBody
    @RequestMapping("/trashpl")
    public JSONObject trashpl(@RequestParam("id") int id) {
    	JSONObject json = new JSONObject();
    	
    	Plotsinfo plotsinfo = this.plotsinfoService.findByPlotsid(id);
    	if(plotsinfo!=null) {
    		//删除全景
    		List<Plotsinfomap> map = this.plotsinfomapService.findByInfoid(plotsinfo.getId());
    		if(map.size() > 0){
				for (int i = 0; i < map.size(); i++) {
					this.plotsinfomapService.del(map.get(i).getId());
				}
			}
    		//删除介绍图
			List<Plotsinfoimg> img = this.plotsinfoimgService.findByInfoid(plotsinfo.getId());
			if(img.size() > 0){
				for (int i = 0; i < img.size(); i++) {
					this.plotsinfoimgService.del(img.get(i).getId());
				}
			}

    		this.plotsinfoService.del(plotsinfo.getId());
    	}

    	Plotsinfonav nav = this.plotsinfonavService.findByPlotsid(id);
    	if(nav != null){
    	    this.plotsinfonavService.del(nav.getId());
        }

        List<Plotsinfoservice> service = this.plotsinfoserviceService.findByPlotsid(id);
    	if(service.size() > 0){
            for (int i = 0; i < service.size(); i++) {
                this.plotsinfoserviceService.del(service.get(i).getId());
            }
        }

    	this.plotsService.del(id);
    	json.put("info", "success");
    	
    	return json;
    }
    
    
    
    @ResponseBody
    @RequestMapping("/getplotsinfoByplotsid")
    public JSONObject getplotsinfoByplotsid(@RequestParam("id") int id) {
    	JSONObject json = new JSONObject();
    	
    	Plotsinfo plotsinfo = this.plotsinfoService.findByPlotsid(id);
    	if(plotsinfo != null){
			List<Plotsinfomap> map = this.plotsinfomapService.findByInfoid(plotsinfo.getId());
			List<Plotsinfoimg> img = this.plotsinfoimgService.findByInfoid(plotsinfo.getId());

			json.put("plotsinfo_map", map);
			json.put("plotsinfo_img", img);
		}
    	json.put("plotsinfo", plotsinfo);
    	return json;
    }
    

    //地块信息
    @ResponseBody
    @RequestMapping("/savePlotsinfo")
    public JSONObject savePlotsinfo(@RequestParam("id") int id,
            @RequestParam("navOnOff") String navOnOff,
    		@RequestParam("paramsMap") String paramsMap,
    		@RequestParam("tipimg") String tipimg,
    		@RequestParam("paramsImg") String paramsImg,
    		@RequestParam("plotsjs") String plotsjs,
    		@RequestParam("PainMapurl") String PainMapurl,
    		@RequestParam("plostpainname") String plostpainname,
            @RequestParam("letour") String letour) {
    	JSONObject json = new JSONObject();
    	Plotsinfo plotsinfo = this.plotsinfoService.findByPlotsid(id);
        Plotsinfonav pnav = this.plotsinfonavService.findByPlotsid(id);
        JSONObject jsonbtn = JSONObject.parseObject(navOnOff);
        if(pnav == null){
            Plotsinfonav plotsinfonav = new Plotsinfonav();
            plotsinfonav.setPlotsid(id);
            plotsinfonav.setUperMapCheack(jsonbtn.getString("UperMapCheack"));
            plotsinfonav.setPlotsJsCheack(jsonbtn.getString("PlotsJsCheack"));
            plotsinfonav.setPlotsYsCheack(jsonbtn.getString("PlotsYsCheack"));
            plotsinfonav.setServiceCheack(jsonbtn.getString("ServiceCheack"));
            plotsinfonav.setMapPainCheack(jsonbtn.getString("MapPainCheack"));
            plotsinfonav.setLetourCheack(jsonbtn.getString("LetourCheack"));
            this.plotsinfonavService.save(plotsinfonav);
        }else{
            pnav.setUperMapCheack(jsonbtn.getString("UperMapCheack"));
            pnav.setPlotsJsCheack(jsonbtn.getString("PlotsJsCheack"));
            pnav.setPlotsYsCheack(jsonbtn.getString("PlotsYsCheack"));
            pnav.setServiceCheack(jsonbtn.getString("ServiceCheack"));
            pnav.setMapPainCheack(jsonbtn.getString("MapPainCheack"));
            pnav.setLetourCheack(jsonbtn.getString("LetourCheack"));
            this.plotsinfonavService.save(pnav);
        }

    	if(plotsinfo == null) {
    		Plotsinfo info = new Plotsinfo();
    		info.setPlotsid(id);
    		info.setPlostintroduce(plotsjs);
    		info.setPlostpain(PainMapurl);
    		info.setPlostpainname(plostpainname);
    		info.setPlostletour(letour);
    		info.setPlosttipimg(tipimg);
    		this.plotsinfoService.save(info);

			int info_id = info.getId();
    		//保存全景地图
			JSONArray array_map = JSONArray.parseArray(paramsMap);
			for (int i = 0; i < array_map.size(); i++) {
				int mapindex = array_map.getJSONObject(i).getInteger("index");
				String mapname = array_map.getJSONObject(i).getString("kname");
				String mapurl = array_map.getJSONObject(i).getString("url");
				if(!mapname.equals("")){
					Plotsinfomap map = new Plotsinfomap();
					map.setMapindex(mapindex);
					map.setMapname(mapname);
					map.setMapurl(mapurl);
					map.setInfoid(info_id);
					this.plotsinfomapService.save(map);
				}
			}
			//保存优势图
			JSONArray array_img = JSONArray.parseArray(paramsImg);
			for (int i = 0; i < array_img.size(); i++) {
				int imgindex = array_img.getJSONObject(i).getInteger("index");
				String imgname = array_img.getJSONObject(i).getString("kname");
				String imgurl = array_img.getJSONObject(i).getString("url");
				if(!imgname.equals("")){
					Plotsinfoimg img = new Plotsinfoimg();
					img.setImgindex(imgindex);
					img.setImgname(imgname);
					img.setImgurl(imgurl);
					img.setInfoid(info_id);
					this.plotsinfoimgService.save(img);
				}
			}
    		json.put("info", "success");
    	}else {
    		plotsinfo.setPlotsid(id);
    		plotsinfo.setPlostintroduce(plotsjs);
    		plotsinfo.setPlostpain(PainMapurl);
    		plotsinfo.setPlostpainname(plostpainname);
            plotsinfo.setPlostletour(letour);
            plotsinfo.setPlosttipimg(tipimg);
    		this.plotsinfoService.save(plotsinfo);

			int plotsinfo_id = plotsinfo.getId();
			//保存全景地图
			List<Plotsinfomap> Plotsinfomap = this.plotsinfomapService.findByInfoid(plotsinfo_id);
			for (int i = 0; i < Plotsinfomap.size(); i++) {
				this.plotsinfomapService.del(Plotsinfomap.get(i).getId());
			}

			JSONArray array_map = JSONArray.parseArray(paramsMap);
			for (int i = 0; i < array_map.size(); i++) {
				int mapindex = array_map.getJSONObject(i).getInteger("index");
				String mapname = array_map.getJSONObject(i).getString("kname");
				String mapurl = array_map.getJSONObject(i).getString("url");
				if(!mapname.equals("")){
					Plotsinfomap map = new Plotsinfomap();
					map.setMapindex(mapindex);
					map.setMapname(mapname);
					map.setMapurl(mapurl);
					map.setInfoid(plotsinfo_id);
					this.plotsinfomapService.save(map);
				}
			}

			//保存优势图
			List<Plotsinfoimg> Plotsinfoimg = this.plotsinfoimgService.findByInfoid(plotsinfo_id);
			for (int i = 0; i < Plotsinfoimg.size(); i++) {
				this.plotsinfoimgService.del(Plotsinfoimg.get(i).getId());
			}

			JSONArray array_img = JSONArray.parseArray(paramsImg);
			for (int i = 0; i < array_img.size(); i++) {
				int imgindex = array_img.getJSONObject(i).getInteger("index");
				String imgname = array_img.getJSONObject(i).getString("kname");
				String imgurl = array_img.getJSONObject(i).getString("url");
				if(!imgname.equals("")){
					Plotsinfoimg img = new Plotsinfoimg();
					img.setImgindex(imgindex);
					img.setImgname(imgname);
					img.setImgurl(imgurl);
					img.setInfoid(plotsinfo_id);
					this.plotsinfoimgService.save(img);
				}
			}
    		json.put("info", "edit");
    	}
    	  	
    	return json;
    }

    @ResponseBody
    @RequestMapping("/getBottomBtn")
    public JSONObject getBottomBtn(@RequestParam("id") int id){
        JSONObject json = new JSONObject();

        Plotsinfonav plotsinfonav = this.plotsinfonavService.findByPlotsid(id);

        json.put("plotsinfonav",plotsinfonav);

        return json;
    }

    @ResponseBody
    @RequestMapping("/getServices")
    public JSONObject getServices(@RequestParam("infoid") int infoid){
        JSONObject json = new JSONObject();

        Plots plots = this.plotsService.findById(infoid);
        List<Plotsinfoservice> ps = this.plotsinfoserviceService.findByPlotsid(infoid);

        json.put("Plotsinfoservice_name",plots.getSername());
        json.put("Plotsinfoservice_list",ps);

        return json;
    }

    @ResponseBody
    @RequestMapping("/getServicesType")
    public JSONObject getServicesType(@RequestParam("id") int id, @RequestParam("type") String type){
        JSONObject json = new JSONObject();

        List<Plotsinfoservice> ps = this.plotsinfoserviceService.findByPlotsidAndSertype(id, type);

        json.put("Plotsinfoservice_list",ps);

        return json;
    }

    @ResponseBody
    @RequestMapping("/saveServices")
    public JSONObject saveServices(@RequestParam("infoid") int infoid,
                                   @RequestParam("sername") String sername,
                                   @RequestParam("serline") String serline,
                                   @RequestParam("sertype") String sertype){
        JSONObject json = new JSONObject();

        Plotsinfoservice ps = new Plotsinfoservice();
        ps.setPlotsid(infoid);
        ps.setSertitle(sername);
        ps.setSerurl(serline);
        ps.setSertype(sertype);
        this.plotsinfoserviceService.save(ps);
        json.put("info","success");

        return json;
    }

    @ResponseBody
    @RequestMapping("/saveServicesT")
    public JSONObject saveServicesT(@RequestParam("infoid") int infoid,
                                   @RequestParam("serTopname") String serTopname){
        JSONObject json = new JSONObject();

        Plots plots = this.plotsService.findById(infoid);
        plots.setSername(serTopname);
        this.plotsService.save(plots);
        json.put("info","success");

        return json;
    }

    @ResponseBody
    @RequestMapping("/editServices")
    public JSONObject editServices(@RequestParam("id") int id,
                                   @RequestParam("sername") String sername,
                                   @RequestParam("serline") String serline,
                                   @RequestParam("sertype") String sertype){
        JSONObject json = new JSONObject();

        Plotsinfoservice ps = this.plotsinfoserviceService.findById(id);
        ps.setSertitle(sername);
        ps.setSerurl(serline);
        ps.setSertype(sertype);
        this.plotsinfoserviceService.save(ps);
        json.put("info","success");

        return json;
    }

    @ResponseBody
    @RequestMapping("/delServices")
    public JSONObject delServices(@RequestParam("id") int id){
        JSONObject json = new JSONObject();

        this.plotsinfoserviceService.del(id);
        json.put("info","success");

        return json;
    }
}
