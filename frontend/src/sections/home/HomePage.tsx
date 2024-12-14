import { FiBox } from 'react-icons/fi'
import { IoTelescopeOutline } from 'react-icons/io5'
import { useNavigate } from 'react-router-dom'
import {
  Box,
  Container,
  Heading,
  HStack,
  Span,
  Text,
  VStack,
} from '@chakra-ui/react'
import { Button } from '@/components/ui/button'
import Footer from '@/components/ui/footer'

const HomePage = () => {
  const navigate = useNavigate()

  const handleOnClick = () => {
    navigate('/pojo')
  }

  return (
    <>
      <Container py="16">
        <VStack gap="10">
          <VStack alignSelf="flex-start" maxW="3xl" gap="5" px="1.5">
            <Heading
              alignSelf="start"
              size={{ base: '4xl', md: '5xl' }}
              as="h1"
            >
              Pojo Generator <br /> herramienta para crear POJOs
            </Heading>
            <Text textStyle="2xl" color="gray.400" fontSize="xl">
              El principal objecto es facilitar el desarrollo al evitar la
              creación manual de clases Java para representar estructuras de
              datos complejas DTOs
            </Text>
          </VStack>
          <Box
            w="full"
            data-state="open"
            _open={{
              animationName: 'fade-in, scale-in',
              animationDuration: '300ms',
            }}
          >
            <Button
              onClick={() => handleOnClick()}
              variant="surface"
              colorPalette="teal"
              w={{ base: 'full', sm: '180px' }}
              size="xl"
            >
              Start Generator
            </Button>
          </Box>
        </VStack>
      </Container>
      <Box as="section" w="full" py="20">
        <Container w="full">
          <HStack justifyContent="space-between">
            <VStack alignItems="flex-start" w="xl" gap="4">
              <Box>
                <Text color="teal.500" fontWeight="semibold">
                  Sistema de generación
                </Text>
              </Box>
              <Heading as="h2" size="5xl">
                Construye tus POJO's con el poder de este sistema
              </Heading>
              <Text textStyle="2xl" color="gray.400" fontSize="xl">
                Dedique menos tiempo escribiendo Clases Java y más tiempo en la
                lógica del negocio.
              </Text>
              <Box as="ul" mt="4" display="flex" flexDir="column" gap="6">
                <Box as="li" display="flex" gap="2" alignItems="center">
                  <FiBox />
                  <Text>
                    Formato.{' '}
                    <Span color="gray.400">
                      Genera Clases Java con un formato de texto
                    </Span>
                  </Text>
                </Box>
                <Box as="li" display="flex" gap="2" alignItems="center">
                  <IoTelescopeOutline />
                  <Text>
                    Lombok.{' '}
                    <Span color="gray.400">
                      Clases Java con anotaciones Lombok
                    </Span>
                  </Text>
                </Box>
              </Box>
            </VStack>
            <VStack w="3xl">Code</VStack>
          </HStack>
        </Container>
      </Box>
      <Footer />
    </>
  )
}

export default HomePage
