# team-project-team03
team-project-team03 created by GitHub Classroom

# What our project dose?

우리의 프로젝트는 도서관 이용자들 데이터를 이용하여 대출 성향을 분석하고, 이용자의 평소 성향과 비슷한 도서는 물론이고 이용자들의 대출 장려를 위해
이용자의 평소 성향과 조금 벗어난 분야의 추천한다.

# How out project dose?

* 개발환경
Java JDK 12.0.1
Maven 3.6.0

추천시스템 모델을 이용하기 위해서는 Mahout Recommendation System API를 이용해야하기 때문에 Maven 설치 및 환경변수 설정이 필요합니다.

https://maven.apache.org/download.cgi

환경에 맞게 binary 파일을 다운받은 후에 Java JDK와 같이 해당경로 \bin에 대해 시스템 환경변수 설정이 필요합니다.

자세한 설치 방법은 아래 사이트 참고.
https://blog.naver.com/hyoun1202/221481448849

# Model
우리는 기존의 Mahout API로 제공하는 Model들을 수정하여 사용하였습니다. 기존의 추천 알고리즘들은 사용자 기반 추천(Collaborative-Filtering)을 사용하고 있는데 이는 사용자의 성향에 가장 알맞는 아이템들을 우선적으로 추천해주기 때문에 우리가 원하는 추천을 지원하지 못 합니다. 저희의 추천은 사용자가 관심이 없었던 분야의 도서 중에서 그나마 나와 비슷한 성향을 지닌 사람들이 좋아했던 도서, 해당 분야에서 인기가 많은 도서를 추천하고자하는 목적을 지니므로 해당 API의 입력값에 수정이 필요했습니다.

Mahout 추천기 모델 중 가장 많이 쓰이는 것은
피어슨 상관관계를 이용한 PearsonCorrelationSimilarity
유클리드 거리법 계산을 이용한 EuclideanDistanceSimilarity
통계 데이터를 이용한 LogLikelihoodSimilarity 등이 있습니다.

저희가 사용한 것은 바로 LogLikelihoodSimilarity 모델인데, 이는 유일하게 boolean 선호도를 지원하는 모델이기 떄문입니다. 다른 추천기들은 사용자들이 해당 아이템에 얼마나의 선호와 리뷰를 주었는지 스코어를 필요로 하지만 도서관 대출 데이터의 특성상 빌렸던 도서인지(T) 아닌지(F)만 판별할 수 있으므로 필연적으로 데이터셋은 boolean data가 됩니다.

Mahout Model의 입력 데이터 셋은 

(UserID, ItemID, Preference)

컬럼의 형태를 갖추고 있는데 LogLikelihoodSimilarity 모델은 Preference 컬럼이 있어도 되고 없어도 괜찮습니다.

# Algorithm

기존의 LogLikelihoodSimilarity은 사용자와 가장 알맞은 아이템을 추천해주기 때문에 내가 편향적인 유저(예를 들어, 인문학만 좋아하는 학생)이라면 필연적으로 인문학 도서만 추천 받게 됩니다. 따라서 우리는 기존의 Model을 사용하되 입력값에 변화를 주어 결과를 뽑아냈습니다.

1. User 각각의 편향도 분석
  - 분산과 표준편차를 이용하여 전체 분야에 대해 얼마나 편향적인 사람인지 계산합니다.
  
2. User 편향도에 따른 새로운 입력 데이터 생성
  - 분야에 대한 표준편차가 일정값 보다 이하 (즉, 어느정도 비편향적인 유저들)을 뽑아내고 그 데이테 중에서 사용자가 가장 비슷한 아이템을 찾기 위함.

3. 새로운 대출 데이터를 분석하여 추천


# Dataset

* 도서 데이터
  - 700권 : Yes24의 장르별 베스트 셀러, 7분야에 대해 각각 100권씩(IT, 에세이, 역사, 예술, 인문, 자기계발, 자연과학)

* 유저 데이터
  - 1000명: 편향적 사용자 700명 (7분야 각 100명), 고른 사용자 300명

* 대출 데이터
  - 100,000건 : 사용자 1,000 * 100권 (각 사용자 100권 대출)
