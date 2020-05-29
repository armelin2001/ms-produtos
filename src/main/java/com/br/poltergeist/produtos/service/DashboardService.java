package com.br.poltergeist.produtos.service;

public class DashboardService extends BaseService{
    /*
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EntityManager em;

    @Autowired
    private CacheManager cacheManager;

    @Cacheable(value = "dashboard", condition = "#cache")
    public RetornoProdutosDashboard consultarProdutosDashboard(@Valid Integer ano,@Valid Boolean cache){
        if(Boolean.FALSE.equals(cache)){
            this.clearCache();
        }
        if(ano == null) {
            ano = Utils.getCurrentYear();
        }
        ProdutosDashboard produtosDashboard = new ProdutosDashboard();
        produtosDashboard.setAno(ano);

        return null;
    }

    @Scheduled(initialDelay = 1800000, fixedRate = 1800000)
    public void clearCache(){
        logger.info("Limpando cache dashboard");
        Cache cache = this.cacheManager.getCache("dashboard");
        if (cache != null) {
            cache.clear();
        }
    }*/




}

