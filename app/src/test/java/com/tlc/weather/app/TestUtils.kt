package com.tlc.weather.app

import com.tlc.weather.app.model.CloudData

object TestUtils {

    val expectedCityNames = listOf<String>("Yafran", "Zuwarah", "Sabratah", "Gharyan", "Zawiya")

    val expectedCityNamesWithEmptyCityNames =
        listOf<String>("Yafran", "Zuwarah", "Sabratah", "", "")

    val expectedCloudData = CloudData(88)

    val expectedWeathers = listOf<String>("Yafran", "Zuwarah", "Sabratah", "Gharyan", "Zawiya")

    val cityListJsonResp = "{\n" +
            "  \"cod\": \"200\",\n" +
            "  \"calctime\": 0.3107,\n" +
            "  \"cnt\": 5,\n" +
            "  \"list\": [\n" +
            "    {\n" +
            "      \"id\": 2208791,\n" +
            "      \"name\": \"Yafran\",\n" +
            "      \"coord\": {\n" +
            "        \"lon\": 12.52859,\n" +
            "        \"lat\": 32.06329\n" +
            "      },\n" +
            "      \"main\": {\n" +
            "        \"temp\": 9.68,\n" +
            "        \"temp_min\": 9.681,\n" +
            "        \"temp_max\": 9.681,\n" +
            "        \"pressure\": 961.02,\n" +
            "        \"sea_level\": 1036.82,\n" +
            "        \"grnd_level\": 961.02,\n" +
            "        \"humidity\": 85\n" +
            "      },\n" +
            "      \"dt\": 1485784982,\n" +
            "      \"wind\": {\n" +
            "        \"speed\": 3.96,\n" +
            "        \"deg\": 356.5\n" +
            "      },\n" +
            "      \"rain\": {\n" +
            "        \"3h\": 0.255\n" +
            "      },\n" +
            "      \"clouds\": {\n" +
            "        \"all\": 88\n" +
            "      },\n" +
            "      \"weather\": [\n" +
            "        {\n" +
            "          \"id\": 500,\n" +
            "          \"main\": \"Rain\",\n" +
            "          \"description\": \"light rain\",\n" +
            "          \"icon\": \"10d\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2208425,\n" +
            "      \"name\": \"Zuwarah\",\n" +
            "      \"coord\": {\n" +
            "        \"lon\": 12.08199,\n" +
            "        \"lat\": 32.931198\n" +
            "      },\n" +
            "      \"main\": {\n" +
            "        \"temp\": 15.36,\n" +
            "        \"temp_min\": 15.356,\n" +
            "        \"temp_max\": 15.356,\n" +
            "        \"pressure\": 1036.81,\n" +
            "        \"sea_level\": 1037.79,\n" +
            "        \"grnd_level\": 1036.81,\n" +
            "        \"humidity\": 89\n" +
            "      },\n" +
            "      \"dt\": 1485784982,\n" +
            "      \"wind\": {\n" +
            "        \"speed\": 5.46,\n" +
            "        \"deg\": 30.0002\n" +
            "      },\n" +
            "      \"clouds\": {\n" +
            "        \"all\": 56\n" +
            "      },\n" +
            "      \"weather\": [\n" +
            "        {\n" +
            "          \"id\": 803,\n" +
            "          \"main\": \"Clouds\",\n" +
            "          \"description\": \"broken clouds\",\n" +
            "          \"icon\": \"04d\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2212771,\n" +
            "      \"name\": \"Sabratah\",\n" +
            "      \"coord\": {\n" +
            "        \"lon\": 12.48845,\n" +
            "        \"lat\": 32.79335\n" +
            "      },\n" +
            "      \"main\": {\n" +
            "        \"temp\": 15.31,\n" +
            "        \"temp_min\": 15.306,\n" +
            "        \"temp_max\": 15.306,\n" +
            "        \"pressure\": 1037.05,\n" +
            "        \"sea_level\": 1037.55,\n" +
            "        \"grnd_level\": 1037.05,\n" +
            "        \"humidity\": 100\n" +
            "      },\n" +
            "      \"dt\": 1485784982,\n" +
            "      \"wind\": {\n" +
            "        \"speed\": 6.71,\n" +
            "        \"deg\": 28.5002\n" +
            "      },\n" +
            "      \"clouds\": {\n" +
            "        \"all\": 92\n" +
            "      },\n" +
            "      \"weather\": [\n" +
            "        {\n" +
            "          \"id\": 804,\n" +
            "          \"main\": \"Clouds\",\n" +
            "          \"description\": \"overcast clouds\",\n" +
            "          \"icon\": \"04d\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2217362,\n" +
            "      \"name\": \"Gharyan\",\n" +
            "      \"coord\": {\n" +
            "        \"lon\": 13.02028,\n" +
            "        \"lat\": 32.172218\n" +
            "      },\n" +
            "      \"main\": {\n" +
            "        \"temp\": 11.23,\n" +
            "        \"temp_min\": 11.231,\n" +
            "        \"temp_max\": 11.231,\n" +
            "        \"pressure\": 1004.23,\n" +
            "        \"sea_level\": 1037.06,\n" +
            "        \"grnd_level\": 1004.23,\n" +
            "        \"humidity\": 90\n" +
            "      },\n" +
            "      \"dt\": 1485784982,\n" +
            "      \"wind\": {\n" +
            "        \"speed\": 3.86,\n" +
            "        \"deg\": 16.5002\n" +
            "      },\n" +
            "      \"rain\": {\n" +
            "        \"3h\": 1.29\n" +
            "      },\n" +
            "      \"clouds\": {\n" +
            "        \"all\": 92\n" +
            "      },\n" +
            "      \"weather\": [\n" +
            "        {\n" +
            "          \"id\": 500,\n" +
            "          \"main\": \"Rain\",\n" +
            "          \"description\": \"light rain\",\n" +
            "          \"icon\": \"10d\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2216885,\n" +
            "      \"name\": \"Zawiya\",\n" +
            "      \"coord\": {\n" +
            "        \"lon\": 12.72778,\n" +
            "        \"lat\": 32.75222\n" +
            "      },\n" +
            "      \"main\": {\n" +
            "        \"temp\": 17,\n" +
            "        \"pressure\": 1024,\n" +
            "        \"humidity\": 55,\n" +
            "        \"temp_min\": 17,\n" +
            "        \"temp_max\": 17\n" +
            "      },\n" +
            "      \"dt\": 1485784982,\n" +
            "      \"wind\": {\n" +
            "        \"speed\": 3.6,\n" +
            "        \"deg\": 10\n" +
            "      },\n" +
            "      \"clouds\": {\n" +
            "        \"all\": 40\n" +
            "      },\n" +
            "      \"weather\": [\n" +
            "        {\n" +
            "          \"id\": 802,\n" +
            "          \"main\": \"Clouds\",\n" +
            "          \"description\": \"scattered clouds\",\n" +
            "          \"icon\": \"03d\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    val cityListJsonRespWithEmptyCityNames = "{\n" +
            "  \"cod\": \"200\",\n" +
            "  \"calctime\": 0.3107,\n" +
            "  \"cnt\": 5,\n" +
            "  \"list\": [\n" +
            "    {\n" +
            "      \"id\": 2208791,\n" +
            "      \"name\": \"Yafran\",\n" +
            "      \"coord\": {\n" +
            "        \"lon\": 12.52859,\n" +
            "        \"lat\": 32.06329\n" +
            "      },\n" +
            "      \"main\": {\n" +
            "        \"temp\": 9.68,\n" +
            "        \"temp_min\": 9.681,\n" +
            "        \"temp_max\": 9.681,\n" +
            "        \"pressure\": 961.02,\n" +
            "        \"sea_level\": 1036.82,\n" +
            "        \"grnd_level\": 961.02,\n" +
            "        \"humidity\": 85\n" +
            "      },\n" +
            "      \"dt\": 1485784982,\n" +
            "      \"wind\": {\n" +
            "        \"speed\": 3.96,\n" +
            "        \"deg\": 356.5\n" +
            "      },\n" +
            "      \"rain\": {\n" +
            "        \"3h\": 0.255\n" +
            "      },\n" +
            "      \"clouds\": {\n" +
            "        \"all\": 88\n" +
            "      },\n" +
            "      \"weather\": [\n" +
            "        {\n" +
            "          \"id\": 500,\n" +
            "          \"main\": \"Rain\",\n" +
            "          \"description\": \"light rain\",\n" +
            "          \"icon\": \"10d\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2208425,\n" +
            "      \"name\": \"Zuwarah\",\n" +
            "      \"coord\": {\n" +
            "        \"lon\": 12.08199,\n" +
            "        \"lat\": 32.931198\n" +
            "      },\n" +
            "      \"main\": {\n" +
            "        \"temp\": 15.36,\n" +
            "        \"temp_min\": 15.356,\n" +
            "        \"temp_max\": 15.356,\n" +
            "        \"pressure\": 1036.81,\n" +
            "        \"sea_level\": 1037.79,\n" +
            "        \"grnd_level\": 1036.81,\n" +
            "        \"humidity\": 89\n" +
            "      },\n" +
            "      \"dt\": 1485784982,\n" +
            "      \"wind\": {\n" +
            "        \"speed\": 5.46,\n" +
            "        \"deg\": 30.0002\n" +
            "      },\n" +
            "      \"clouds\": {\n" +
            "        \"all\": 56\n" +
            "      },\n" +
            "      \"weather\": [\n" +
            "        {\n" +
            "          \"id\": 803,\n" +
            "          \"main\": \"Clouds\",\n" +
            "          \"description\": \"broken clouds\",\n" +
            "          \"icon\": \"04d\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2212771,\n" +
            "      \"name\": \"Sabratah\",\n" +
            "      \"coord\": {\n" +
            "        \"lon\": 12.48845,\n" +
            "        \"lat\": 32.79335\n" +
            "      },\n" +
            "      \"main\": {\n" +
            "        \"temp\": 15.31,\n" +
            "        \"temp_min\": 15.306,\n" +
            "        \"temp_max\": 15.306,\n" +
            "        \"pressure\": 1037.05,\n" +
            "        \"sea_level\": 1037.55,\n" +
            "        \"grnd_level\": 1037.05,\n" +
            "        \"humidity\": 100\n" +
            "      },\n" +
            "      \"dt\": 1485784982,\n" +
            "      \"wind\": {\n" +
            "        \"speed\": 6.71,\n" +
            "        \"deg\": 28.5002\n" +
            "      },\n" +
            "      \"clouds\": {\n" +
            "        \"all\": 92\n" +
            "      },\n" +
            "      \"weather\": [\n" +
            "        {\n" +
            "          \"id\": 804,\n" +
            "          \"main\": \"Clouds\",\n" +
            "          \"description\": \"overcast clouds\",\n" +
            "          \"icon\": \"04d\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2217362,\n" +
            "      \"name\": \"\",\n" +
            "      \"coord\": {\n" +
            "        \"lon\": 13.02028,\n" +
            "        \"lat\": 32.172218\n" +
            "      },\n" +
            "      \"main\": {\n" +
            "        \"temp\": 11.23,\n" +
            "        \"temp_min\": 11.231,\n" +
            "        \"temp_max\": 11.231,\n" +
            "        \"pressure\": 1004.23,\n" +
            "        \"sea_level\": 1037.06,\n" +
            "        \"grnd_level\": 1004.23,\n" +
            "        \"humidity\": 90\n" +
            "      },\n" +
            "      \"dt\": 1485784982,\n" +
            "      \"wind\": {\n" +
            "        \"speed\": 3.86,\n" +
            "        \"deg\": 16.5002\n" +
            "      },\n" +
            "      \"rain\": {\n" +
            "        \"3h\": 1.29\n" +
            "      },\n" +
            "      \"clouds\": {\n" +
            "        \"all\": 92\n" +
            "      },\n" +
            "      \"weather\": [\n" +
            "        {\n" +
            "          \"id\": 500,\n" +
            "          \"main\": \"Rain\",\n" +
            "          \"description\": \"light rain\",\n" +
            "          \"icon\": \"10d\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2216885,\n" +
            "      \"name\": \"\",\n" +
            "      \"coord\": {\n" +
            "        \"lon\": 12.72778,\n" +
            "        \"lat\": 32.75222\n" +
            "      },\n" +
            "      \"main\": {\n" +
            "        \"temp\": 17,\n" +
            "        \"pressure\": 1024,\n" +
            "        \"humidity\": 55,\n" +
            "        \"temp_min\": 17,\n" +
            "        \"temp_max\": 17\n" +
            "      },\n" +
            "      \"dt\": 1485784982,\n" +
            "      \"wind\": {\n" +
            "        \"speed\": 3.6,\n" +
            "        \"deg\": 10\n" +
            "      },\n" +
            "      \"clouds\": {\n" +
            "        \"all\": 40\n" +
            "      },\n" +
            "      \"weather\": [\n" +
            "        {\n" +
            "          \"id\": 802,\n" +
            "          \"main\": \"Clouds\",\n" +
            "          \"description\": \"scattered clouds\",\n" +
            "          \"icon\": \"03d\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    val weatherDetailsJsonResp = "{\n" +
            "  \"id\": 2208791,\n" +
            "  \"name\": \"Yafran\",\n" +
            "  \"coord\": {\n" +
            "    \"lon\": 12.52859,\n" +
            "    \"lat\": 32.06329\n" +
            "  },\n" +
            "  \"main\": {\n" +
            "    \"temp\": 9.68,\n" +
            "    \"temp_min\": 9.681,\n" +
            "    \"temp_max\": 9.681,\n" +
            "    \"pressure\": 961.02,\n" +
            "    \"sea_level\": 1036.82,\n" +
            "    \"grnd_level\": 961.02,\n" +
            "    \"humidity\": 85\n" +
            "  },\n" +
            "  \"dt\": 1485784982,\n" +
            "  \"wind\": {\n" +
            "    \"speed\": 3.96,\n" +
            "    \"deg\": 356.5\n" +
            "  },\n" +
            "  \"rain\": {\n" +
            "    \"3h\": 0.255\n" +
            "  },\n" +
            "  \"clouds\": {\n" +
            "    \"all\": 88\n" +
            "  },\n" +
            "  \"weather\": [\n" +
            "    {\n" +
            "      \"id\": 500,\n" +
            "      \"main\": \"Rain\",\n" +
            "      \"description\": \"light rain\",\n" +
            "      \"icon\": \"10d\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"sys\": {\n" +
            "    \"type\": 1,\n" +
            "    \"id\": 8166,\n" +
            "    \"message\": 0.2064,\n" +
            "    \"country\": \"AU\",\n" +
            "    \"sunrise\": 1485840694,\n" +
            "    \"sunset\": 1485891062\n" +
            "  }\n" +
            "}"
}